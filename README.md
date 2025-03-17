# Rocky Dry Cleaners Management System

## Overview
The Rocky Dry Cleaners Management System is a Java application designed to help manage customer orders for a dry cleaning business. It provides a graphical user interface for entering customer data, calculating charges, and storing order information.

This application was created for my Programming Fundamentals cource at Central Queensland University. This was the first program I made.

## Features
- **Customer Order Entry**: Enter customer names and the number of garments to be cleaned
- **Automatic Pricing**: System automatically calculates charges based on number of garments
  - 1-2 garments: $8.50 per garment
  - 3 garments: Flat rate of $20.00
  - 4+ garments: $20.00 + $6.50 for each additional garment beyond 3
- **Order Management**: Store up to 10 customer orders in a session
- **Order Display**: View all entered orders along with statistics (average garments, total charges)
- **Search Functionality**: Look up orders by customer name
- **User-Friendly Interface**: Simple GUI for easy data entry and retrieval

## System Requirements
- Java Runtime Environment (JRE)
- Graphical display support

## How to Run
1. Compile both Java files:

  `javac Order.java RockyDryCleanersGUI.java`

2. Run the application:

  `java RockyDryCleanersGUI`


## Application Structure
**RockyDryCleanersGUI.java**: Main application class that handles the user interface and user interactions
**Order.java**: Class that represents individual customer orders and handles charge calculations

## Usage Instructions
1. Enter the customer name in the "Customer name" field
2. Enter the number of garments in the "Number of plain garments" field
3. Click "Enter" to submit the order
4. Use "Display All" to view all orders and statistics
5. Use "Search" to find a specific customer's order
6. Click "Exit" to close the application