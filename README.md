# customer-service-ci-cd

🧍 Customer Service - Microservice
This is a Spring Boot-based microservice responsible for managing customer-related operations. It is part of a microservices architecture where each service is independently deployable, containerized using Docker, orchestrated with Kubernetes, and integrated into a CI/CD pipeline.

🚀 Features
Add, update, and delete customer data

RESTful API endpoints

Standalone microservice

Dockerized for container deployment

Kubernetes-ready

Integrated with GitHub Actions for CI/CD

Cloud-ready: deployed to AWS S3

📁 Project Structure

customer-service/
├── src/
├── target/
├── Dockerfile
├── pom.xml
├── application.properties
└── k8s/
    ├── deployment.yaml
    └── service.yaml
    
🛠️ Build and Run the Project
Step 1: Clone the Repository
bash
Copy
Edit
git clone https://github.com/your-username/microservices-project.git
cd microservices-project/customer-service
Step 2: Build the Project
bash
Copy
Edit
mvn clean install
Step 3: Run Locally
bash
Copy
Edit
java -jar target/customer-service.jar

📦 Docker Integration
Dockerfile
Dockerfile
Copy
Edit
FROM openjdk:23
WORKDIR /app
COPY target/customer-service.jar customer-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "customer-service.jar"]
Build Docker Image
bash
Copy
Edit
docker build -t customer-service:v1 .
Run Docker Container
bash
Copy
Edit
docker run -d -p 8081:8081 customer-service:v1

![image](https://github.com/user-attachments/assets/d70cacc9-d5ce-41cc-89d2-5722686e7a88)



☸️ Kubernetes Deployment
Step 1: Start Minikube
bash
Copy
Edit
minikube start --driver=docker
Step 2: Apply Kubernetes Configs
deployment.yaml
yaml
Copy
Edit
apiVersion: apps/v1
kind: Deployment
metadata:
  name: customer-service
spec:
  replicas: 2
  selector:
    matchLabels:
      app: customer
  template:
    metadata:
      labels:
        app: customer
    spec:
      containers:
      - name: customer
        image: customer-service:v1
        ports:
        - containerPort: 8081
        
service.yaml
yaml
Copy
Edit
apiVersion: v1
kind: Service
metadata:
  name: customer-service
spec:
  selector:
    app: customer
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8081
  type: NodePort
Apply the Configuration
bash
Copy
Edit
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
Access the Service
bash
Copy
Edit
minikube service customer-service

![image](https://github.com/user-attachments/assets/56eb767d-4cfe-42f1-bee2-4a97cf52de54)

# Kubernets Dashboard

![image](https://github.com/user-attachments/assets/fe020fcc-02d9-4a29-92b9-d2eafd8457f0)

![image](https://github.com/user-attachments/assets/ad6fcd92-eaf3-4de4-a221-39304f378012)


☁️ AWS S3 Upload (Optional)
If you want to store the build on AWS S3:

bash
Copy
Edit
aws s3 mb s3://customer-service-build
aws s3 cp target/customer-service.jar s3://customer-service-build/

![image](https://github.com/user-attachments/assets/030e1222-c5ee-48a8-bf66-418209c12787)


🔁 CI/CD with GitHub Actions
Create .github/workflows/deploy-customer.yml:

yaml
Copy
Edit
name: Customer Service CI/CD

on:
  push:
    branches: [ main ]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest

# CI/CD Pipeline

![image](https://github.com/user-attachments/assets/96d89008-c635-40bf-a7b3-53078a792ed0)



# GenAI project using Python

EchoMate

Developed a voice-controlled virtual assistant using Python, integrating speech recognition, text-to-speech, and automation capabilities. o Implemented features like YouTube song playback, time announcements, Wikipedia searches, and joke generation using PyWhatKit, Wikipedia API, and PyJokes. o Enhanced user interaction with real-time voice recognition and response, enabling seamless command execution and improving accessibility.

#Importing Libraries
import speech_recognition as sr
import pyttsx3
import pywhatkit
import datetime
import wikipedia
import pyjokes
import sys



listener = sr.Recognizer()

engine = pyttsx3.init()
voices = engine.getProperty("voices")
engine.setProperty('voice', voices[1].id)

def engine_talk(text):
    engine.say(text)
    engine.runAndWait()
    

def user_commands():
    try:
        with sr.Microphone() as source:
            print("Start Speaking!!")
            voice = listener.listen(source)
            command = listener.recognize_google(voice)
            command = command.lower()
            if 'alexa' in command:
                command = command.replace('alexa', '')
                print(command)
    except:
        pass
    return command
    
    
def run_alexa():
    command = user_commands()
    
    if 'play' in command:
        song = command.replace('play', '')
        engine_talk('Playing' +song)
        pywhatkit.playonyt(song)
    
    elif 'time' in command:
        time = datetime.datetime.now().strftime('%I:%M %p')
        engine_talk('The current time is' +time)
    
    elif 'who is' in command:
        name = command.replace('who is' , '')
        info =  wikipedia.summary(name, 1)
        print(info)
        engine_talk(info)
    
    elif 'joke' in command:
        engine_talk(pyjokes.get_joke())
    
    elif 'stop' in command:
        sys.exit()
    
    else:
        engine_talk('I could not hear you properly')
        
        
while True:
    run_alexa()

