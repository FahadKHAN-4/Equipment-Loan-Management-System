import java.util.Date;

public class Day implements Cloneable {
	
	private int year;
	private int month;
	private int day;

	boolean first = true;

	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";
	
	//Constructor
	public Day(String sDay) {
		try {
			set(sDay);
		} catch (ExInvalidDate e) {
			e.getMessage();
		} 
	}

	public Day(int year2, int month2, int day2) {
		this.year = year2;
		this.month = month2;
		this.day = day2;
	}

	public void set(String sDay) throws ExInvalidDate  { //Set year,month,day based on a string like 01-Mar-2021 
		String[] sDayParts = sDay.split("-");

		int y,m,d;

		if(sDayParts.length<3){
			throw new ExInvalidDate();
		}else if(MonthNames.contains(sDayParts[1]) == false){
			throw new ExInvalidDate();
		}else {

			try{
				y = Integer.parseInt(sDayParts[2]); //Apply Integer.parseInt for sDayParts[2];
				d = Integer.parseInt(sDayParts[0]);
				m = MonthNames.indexOf(sDayParts[1])/3+1;
			} catch (NumberFormatException e){
				throw new ExInvalidDate();
			}
		}
		
		if(valid(y, m, d) == false){
			throw new ExInvalidDate();
		}else {
			if(first == true){
				this.year = Integer.parseInt(sDayParts[2]); //Apply Integer.parseInt for sDayParts[2];
				this.day = Integer.parseInt(sDayParts[0]);
				this.month = MonthNames.indexOf(sDayParts[1])/3+1;
				first = false;

			} else{

				if(y<this.year){
					throw new ExInvalidDate("Invalid new day.  The new day has to be later than the current date " + toString() +"." );
				} else if(this.year == y && this.month > m){
					throw new ExInvalidDate("Invalid new day.  The new day has to be later than the current date " + toString() +"." );
				} else if(this.year == y && this.month == m && this.day > d){
					throw new ExInvalidDate("Invalid new day.  The new day has to be later than the current date "+ toString() +".");
				}else{
					this.year = Integer.parseInt(sDayParts[2]); //Apply Integer.parseInt for sDayParts[2];
					this.day = Integer.parseInt(sDayParts[0]);
					this.month = MonthNames.indexOf(sDayParts[1])/3+1;
				}

			}
			
		}
	
	}

	public void set(String sOld, String string) {
		String[] sDayParts = sOld.split("-");
		this.year = Integer.parseInt(sDayParts[2]); //Apply Integer.parseInt for sDayParts[2];
		this.day = Integer.parseInt(sDayParts[0]);
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;
    }
	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d) {
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	public String nextDay(int d) {
		switch(month){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					if(d + this.day <=31){
						Day dDay = new Day(year, month, day+d);
						return dDay.toString();
					}else{
						Day dDay = new Day(year, month+1, (d-(31-day)));
						return dDay.toString();
					}
			case 4: case 6: case 9: case 11:
					if(d + this.day <=30){
						Day dDay = new Day(year, month, day+d);
						return dDay.toString();
					}else{
						Day dDay = new Day(year, month+1, (d-(31-day)));
						return dDay.toString();
					} 
			case 2:
					if (isLeapYear(year)){
						if(d + this.day <=29){
							Day dDay = new Day(year, month, day+d);
							return dDay.toString();
						}else{
							Day dDay = new Day(year, month+1, (d-(29-day)));
							return dDay.toString();
						}
					}else{
						if(d + this.day <=28){
							Day dDay = new Day(year, month, day+d);
							return dDay.toString();
						}else{
							Day dDay = new Day(year, month+1, (d-(28-day)));
							return dDay.toString();
						}
					}		 
		}
		return null;
	}

	// Return a string for the day like dd MMM yyyy
	@Override
	public String toString() {		
		return day+"-"+ MonthNames.substring((month-1)*3, (month)*3) + "-"+ year; // (month-1)*3,(month)*3
	}

	
	@Override
	public Day clone() {
		Day copy = null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		return copy;
	}

}
