🔐 CRYPTOGRAPHY_JAVA_SWING

A simple Java Swing-based desktop application that allows users to encrypt and decrypt string messages using a symmetric key and send encrypted messages via email using the JavaMail API. The receiver must possess the same key to decrypt the message.

📌 Features

🔒 Symmetric key encryption and decryption

📧 Send encrypted messages to any email

🖥️ Simple and intuitive Java Swing GUI

🔐 Secure key sharing (user must share key manually)

✅ Same key used for encryption and decryption

🛠️ Tech Stack

Java (Core)

Java Swing – for the GUI

JavaMail API – for sending emails

Symmetric Key Cryptography – for encryption & decryption

📦 Prerequisites

Make sure you have the following installed:

✅ Java JDK 8+

✅ Maven (for dependency management and packaging)

✅ Internet connection (for sending emails via SMTP)

🚀 How to Run Locally

Clone the Repository

git clone https://github.com/your-username/cryptography.git
cd cryptography

Package the App (Executable JAR)

mvn clean package

Run the Application

java -jar target/cryptography-app-1.0.jar

💡 Alternatively, double-click the generated .jar file (only works if properly shaded and you have a GUI environment).

📸 Screenshots

![image](https://github.com/user-attachments/assets/67d0d21f-dc77-44c1-ae9e-dde5457db415)


🧑‍💻 Author

Ankit PandeyGitHub • LinkedIn (https://www.linkedin.com/in/ankitpandeyap/)
