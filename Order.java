/*
Programmer: Lucas Mathews 12070730
Course: Programming Fundamentals COIT11222
File: Order.java
Purpose: Assignment Two -- Rocky Dry Cleaners windowed application
Date: 19 May 2019
*/

public class Order  //page 141 does not contain a main, organising classes
	{

		private String customerName = "";
		private int garments = 0;

		public Order() //Default constructor, name is set to "" and gar is set to 0
			{
				this("", 0);
			}

		public Order(String name, int gar) //Parameterised constructor
			{
				customerName = name;
				garments = gar;
			}


			public void setCustomerName(String name)
			{
			   customerName = name;
			}


			public void setGarments(int gar)
			{
			   garments = gar;
			}



			public String getCustomerName() //returns data to RockyDryCleanersGUI.java
			{
				return customerName;
			}


			public int getGarments() //returns data to RockyDryCleanersGUI.java
			{
				return garments;
			}



			public double calculateCharge() //calculates the charge for the garments, calculation code from ASS1
				{
					double garmentCost = 8.50;
					double garmentDiscount = 6.50;
					double charge = 0; //do not store charge as an instance variable.. can I store it straight to array?

					if (garments == 1 || garments == 2)
						charge = garmentCost * garments;

					else if (garments == 3)
						charge = 20;

					else
						charge = 20 + (garmentDiscount * (garments - 3));

					return charge;
				}

	}
