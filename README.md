# 📦 Parcel Depot Management System

This project is a Parcel Depot Management System, designed to simulate and manage the process of receiving, storing, and collecting parcels. The system provides an interactive GUI for depot staff to process customers, manage parcels, generate reports, and track parcel statuses efficiently.

## 🏗 Project Structure

The project follows the Model-View-Controller (MVC) architecture:

Model (model/) – Handles data structures, including Parcel, Customer, and ParcelMap.
View (view/) – Manages the GUI using MainFrame.java, presenting tables for customers and parcels.
Controller (controller/) – Manages logic, processes customers, and updates parcel status using Manager and Worker classes.

## 📜 Features

- ✅ Load customers & parcels from CSV files
- ✅ Process customer collections
- ✅ Update parcel status (waiting → collected)
- ✅ Generate detailed reports (GeneratedReport.txt)
- ✅ Add new customers & parcels dynamically
- ✅ Find parcels by ID
- ✅ Color-coded parcel status (Green = Collected, Red = Waiting)
- ✅ Alternating row colors for better readability
- ✅ Interactive summary panel

## 🛠 Technologies Used

- Java 21 (JDK)
- Swing (GUI)
- Java Collections Framework (Queue, List, Map)
- File I/O (CSV handling & report generation)

## 📁 Project Files
```
📂 src/
 ┣ 📂 controller/     # Handles business logic (Manager, Worker)
 ┣ 📂 model/          # Data models (Parcel, Customer, ParcelMap, Log)
 ┣ 📂 view/           # GUI Components (MainFrame, Table Rendering)
 ┣ 📜 Main.java       # Entry point of the application
 ┗ 📂 resources/      # CSV Data & Reports
```
## 🔧 How to Run

**Clone the repository**

```
git clone https://github.com/yourusername/Parcel-Depot-Management.git
cd Parcel-Depot-Managemen
```

**Compile & Run**

```
javac -d out -sourcepath src src/Main.java
java -cp out view.Main
```

## 📊 Report Generation

After processing customers, a detailed report is automatically saved in:
```
resources/GeneratedReport.txt
```

The report includes:

- Total customers processed
- Total parcels collected
- Total fees collected
- Pending parcels in the depot
- Recently added customers & parcels

# 🏗 Future Improvements

- 🔹 Improve GUI with a dashboard summary
- 🔹 Implement database storage instead of CSV files
- 🔹 Add user authentication for security
