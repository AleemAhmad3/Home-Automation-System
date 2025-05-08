Home Automation Android App
Overview
This project is a feature-rich home automation system that allows users to control and monitor various household devices remotely. Built using XML, Java, and Firebase, the app interfaces with an Arduino NodeMCU to manage devices and gather environmental data, such as temperature and humidity. The project comprises five key modules: Authentication, Profile Management, Settings, Device Control, and Temperature & Humidity Monitoring.

Features
1. Authentication
Secure login and registration using Firebase Authentication.
Email and password-based authentication.
Google Sign-in.
Password recovery via email.
3. Profile Management
User profile creation and management.
Edit and update user information.
Store user preferences and settings in Firebase Firestore.
4. Settings
Customize app settings according to user preferences.
Control notification settings, theme options, and more.
Save and retrieve settings from Firebase.
5. Device Control
Remotely control home devices via the app.
Interface with Arduino NodeMCU for device management.
Real-time updates of device status.
6. Temperature & Humidity Monitoring
Display real-time temperature and humidity data from sensors connected to the Arduino NodeMCU.
Graphical representation of environmental data.
Historical data storage and retrieval.
Hardware Requirements
Arduino NodeMCU: Microcontroller for interfacing with sensors and relays.
Temperature and Humidity Sensors: To monitor the environment.
Relay Modules: To control home devices like lights, fans, etc.
Power Supply: Adequate power supply for Arduino and connected devices.
Software Requirements
Android Studio: For developing and running the Android app.
Arduino IDE: For programming the NodeMCU.
Firebase: Backend services including Authentication, Firestore, and Realtime Database.
Java and XML: For app development.
Setup and Installation
1. Clone the Repository
bash
Copy code
git clone https://github.com/AleemAhmad3/Home-Automation-System.git

2. Configure Firebase
Create a Firebase project.
Add your Android app to the Firebase project.
Download the google-services.json file and place it in the app/ directory.
Enable Firebase Authentication and Firestore.
3. Configure Arduino NodeMCU
Set up the Arduino IDE with the required libraries (e.g., DHT, ESP8266WiFi).
Upload the Arduino code from the arduino/ directory to your NodeMCU.
4. Build and Run the App
Open the project in Android Studio.
Sync the project with Gradle files.
Build and run the app on your Android device or emulator.
Usage
Authentication: Log in or sign up with your credentials.
Profile Management: Edit your profile and manage your settings.
Device Control: Navigate to the Device Control module to manage connected devices.
Temperature & Humidity: View real-time environmental data on the dashboard.
Contributing
Contributions are welcome! Please submit a pull request or open an issue to discuss any changes or improvements.

Contact
For any inquiries, please reach out to ahmadaleem592@gmail.com
