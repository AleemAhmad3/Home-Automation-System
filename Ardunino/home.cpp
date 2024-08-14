#include <ESP8266WiFi.h>
#include <FirebaseESP8266.h>
#include <DHT.h>

// Replace these with your network credentials
#define WIFI_SSID "your_SSID"
#define WIFI_PASSWORD "your_PASSWORD"

// Replace with your Firebase project credentials
#define FIREBASE_HOST "your_project_id.firebaseio.com"
#define FIREBASE_AUTH "your_firebase_database_secret"

// DHT sensor settings
#define DHTPIN D4          // Pin where the data pin of the DHT sensor is connected
#define DHTTYPE DHT11      // DHT 11 or DHT 22
DHT dht(DHTPIN, DHTTYPE);

// Firebase paths
#define DEVICE_CONTROL_PATH "/devices/"
#define TEMP_HUMID_PATH "/environment/"

// Device control pins
#define RELAY1_PIN D1      // Pin connected to Relay 1
#define RELAY2_PIN D2      // Pin connected to Relay 2

// Firebase instance
FirebaseData firebaseData;

void setup() {
  // Initialize serial communication
  Serial.begin(115200);

  // Initialize the DHT sensor
  dht.begin();

  // Initialize relay pins
  pinMode(RELAY1_PIN, OUTPUT);
  pinMode(RELAY2_PIN, OUTPUT);
  digitalWrite(RELAY1_PIN, HIGH); // Initially turn off the relay
  digitalWrite(RELAY2_PIN, HIGH); // Initially turn off the relay

  // Connect to Wi-Fi
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print(".");
  }
  Serial.println();
  Serial.println("Connected to Wi-Fi");

  // Connect to Firebase
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);

  // Initial setup: sync device control with Firebase
  syncDeviceState();
}

void loop() {
  // Read the temperature and humidity from the sensor
  float humidity = dht.readHumidity();
  float temperature = dht.readTemperature();

  // Check if readings are valid
  if (isnan(humidity) || isnan(temperature)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Upload temperature and humidity to Firebase
  Firebase.setFloat(firebaseData, TEMP_HUMID_PATH "temperature", temperature);
  Firebase.setFloat(firebaseData, TEMP_HUMID_PATH "humidity", humidity);

  // Read device control commands from Firebase
  checkDeviceControl();

  // Wait for a short period before the next loop
  delay(2000);
}

// Function to sync the initial state of devices with Firebase
void syncDeviceState() {
  if (Firebase.getBool(firebaseData, DEVICE_CONTROL_PATH "device1")) {
    digitalWrite(RELAY1_PIN, firebaseData.boolData() ? LOW : HIGH);
  }
  if (Firebase.getBool(firebaseData, DEVICE_CONTROL_PATH "device2")) {
    digitalWrite(RELAY2_PIN, firebaseData.boolData() ? LOW : HIGH);
  }
}

// Function to check device control commands from Firebase
void checkDeviceControl() {
  // Check for updates in the device control paths
  if (Firebase.getBool(firebaseData, DEVICE_CONTROL_PATH "device1")) {
    bool device1State = firebaseData.boolData();
    digitalWrite(RELAY1_PIN, device1State ? LOW : HIGH);
  }

  if (Firebase.getBool(firebaseData, DEVICE_CONTROL_PATH "device2")) {
    bool device2State = firebaseData.boolData();
    digitalWrite(RELAY2_PIN, device2State ? LOW : HIGH);
  }
}
