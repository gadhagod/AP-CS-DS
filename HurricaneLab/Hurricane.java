/**
 * Models hurricane information, including categories.  
 * Represents a hurricane with its year, month, pressure,
 * speed, and name. Allows you to compare these hurricanes
 * with any of the above fields.
 * @author  Aarav Borthakur & Susan King
 * @version 03/01/23
 */
public class Hurricane
{
    private int year; 
    private String month;
    private int pressure;
    private int speed;
    private String name;
    private int category;

    /**
     * Initializes a Hurricane object with no information.
     */
    public Hurricane()
    {
    }

    /**
     * Initializes a Hurricane object with historical information.
     * @param year      year the hurricane took place
     * @param month     month in String format
     * @param pressure  hurricane's pressure
     * @param speed     hurricane's speed in knots
     * @param name      hurricane's name
     */
    public Hurricane(int year, String month, int pressure, int speed, String name)
    {
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
        this.category = determineCategory(speed);
    }

    /**
     * Based upon Saffir/Simpson Hurricane Scale, figures out
     * the category using wind speed in knots.
     * @param knots     wind speed in knots
     * @return Saffir/Simpson Hurricane Scale category
     */
    public int determineCategory(int knots)
    {
        if (knots >= 137)
        {
            return 5;
        }
        if (knots >= 113)
        {
            return 4;
        }
        if (knots >= 96)
        {
            return 3;
        }
        if (knots >= 83)
        {
            return 2;
        }
        return 1;
    }

    /**
     * Gets the name of the Hurricane
     * @return      The name of the Hurricane
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the month that the Hurricane
     * occurred
     * @return      The month of the Hurricane
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Gets the pressure of the Hurricane
     * @return      The pressure of the Hurricane
     */
    public int getPressure()
    {
        return pressure;
    }

    /**
     * Gets the wind speed of the hurricane
     * @return      The wind speed of the hurricane
     *              in knots
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Gets the year that the Hurricane
     * occurred
     * @return      The year of the Hurricane
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Gets the category the Hurricane through 
     * the Saffir/Simpson Hurricane Scale
     * @return      The category of the Hurricane
     */
    public int getCategory()
    {
        return category;
    }

    /**
     * Prints the String representation of the Hurricane
     */
    public void print()
    {
        System.out.println(toString( ));
    }

    /**
     * Gets the String representation of the Hurricane
     * @return  The String representation of the Hurricane
     *          with its year, month, name, category, speed
     *          and pressure
     */
    public String toString()
    {
        return String.format("%-4d %-5s %-15s %-5d %5d %5d ", 
            year, month, name, getCategory(), speed, pressure);
    }

    /**
     * Compares the years of two Hurricanes
     * @param h The other Hurricane to compare with
     * @return  A value == 0 if the Hurricanes'
     *          years are equal, > 0 if this 
     *          Hurricane's year is greater,    
     *          and < 0 if this Hurricane's year 
     *          is lesser
     */
    public int compareYearTo(Hurricane h)
    {
        return year - h.getYear();
    }

    /**
     * Lexicographically compares the years of two 
     * Hurricanes
     * @param h The other Hurricane to compare with
     * @return  A value == 0 if the Hurricanes'
     *          names are equal, > 0 if this 
     *          Hurricane's name is lexographically 
     *          greater, and < 0 if this Hurricane's name
     *          is lexographically lesser
     */
    public int compareNameTo(Hurricane h)
    {
        return name.compareTo(h.getName());
    }

    /**
     * Compares the pressures of two Hurricanes
     * @param h The other Hurricane to compare with
     * @return  A value == 0 if the Hurricanes'
     *          pressures are equal, > 0 if this 
     *          Hurricane's pressure is greater,    
     *          and < 0 if this Hurricane's pressure
     *          is lesser
     */
    public int comparePressureTo(Hurricane h)
    {
        return pressure - h.getPressure();
    }

    /**
     * Compares the speed of two Hurricanes
     * @param h The other Hurricane to compare with
     * @return  A value == 0 if the Hurricanes'
     *          speed are equal, > 0 if this 
     *          Hurricane's speed is greater,    
     *          and < 0 if this Hurricane's speed
     *          is lesser
     */
    public int compareSpeedTo(Hurricane h)
    {
        return speed - h.getSpeed();
    }

    /**
     * Compares the Saffir/Simpson category of two 
     * Hurricanes
     * @param h The other Hurricane to compare with
     * @return  A value == 0 if the Hurricanes'
     *          category are equal, > 0 if this 
     *          Hurricane's category is greater,    
     *          and < 0 if this Hurricane's category
     *          is lesser
     */
    public int compareCategoryTo(Hurricane h)
    {
        return getCategory() - h.getCategory();
    }
}
