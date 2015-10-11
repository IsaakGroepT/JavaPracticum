package model;

import java.util.Date;

/**
 * 
 * @author Isaak
 *
 */
public class Datum {

	private int dag;
	private int maand;
	private int jaar;
	
	private String[] maanden = {"januari", "feburari", "maart", "april", "mei",
			"juni", "juli", "augustus", "september", "oktober", "november", "december"};
	
	public static void main(String[] args)
	{
		Datum d = new Datum(1, 10, 2014);
		System.out.println(d.getDatumInEuropeesFormaat());
	}
	
	/**
	 * 
	 */
	public Datum()
	{
		try {
			HuidigeSysteemDatum();
			
			// Voor de zekerheid ook valideren, want misschien is er anders een security hole?
			datumValidatie();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param Datum
	 */
	public Datum(Date Datum)
	{
		try {
			setDag(Datum.getDate());
			setMaand(Datum.getMonth());
			setJaar(Datum.getYear());
			
			datumValidatie();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param dag
	 * @param maand
	 * @param jaar
	 */
	public Datum(int dag, int maand, int jaar)
	{
		try {
			setDag(dag);
			setMaand(maand);
			setJaar(jaar);
			
			datumValidatie();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
    /**
     * Datum als string DDMMJJJJ
     * @param datum 
     */
	public Datum(String datum)
	{
		try {
			String[] datumDelen = datum.split("/");
			
			setDag(Integer.parseInt(datumDelen[0]));
			setMaand(Integer.parseInt(datumDelen[1]));
			setJaar(Integer.parseInt(datumDelen[2]));
			
			datumValidatie();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
    
	/**
	 * 
	 * @param dag
	 * @param maand
	 * @param jaar
	 * @return
	 */
    public boolean setDatum(int dag, int maand, int jaar)
    {
    	try {
	    	setDag(dag);
	        setMaand(maand);
	        setJaar(jaar);
	        
	        datumValidatie();
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        
        return true;
    }
    
    /**
     * 
     * @return
     */
    public String getDatumInAmerikaansFormaat()
    {
    	return String.format("%04d/%02d/%02d", jaar, maand, dag);
    }
    
    /**
     * 
     * @return
     */
    public String getDatumInEuropeesFormaat()
    {
    	return String.format("%02d/%02d/%04d", dag, maand, jaar);
    }
    
    /**
     * 
     * @param datum
     * @return
     */
    public boolean kleinerDan(Datum datum)
    {
    	return compareTo(datum) < 0;
    }
    
    /**
     * 
     */
    @Override
    public boolean equals(Object obj)
    {
    	// Is het exact hetzelfde object?
    	if (this == obj)
    	{
    		return true;
    	}
    	
    	// Is het hetzelfde type?
    	if (obj == null || !(obj instanceof Datum))
    	{
    		return false;
    	}
    	// Nu zien of de inhoud dezelfde is
    	return compareTo((Datum) obj) == 0;
    }
    
    /**
     * Ik snap er de ballen van
     */
    @Override
	public int hashCode()
	{
		final int prime = 37;
		int hash = 1;
		
		hash = prime * hash + dag;
		hash = prime * hash + maand;
		hash = prime * hash + jaar;
		return hash;
	}
    
    /**
     * Vergelijk de onze datum met de nieuwe
     */
    public int compareTo(Datum datum2)
    {
    	if (jaar > datum2.jaar)
    	{
    		return 1;
    	}
    	else if (jaar < datum2.jaar)
    	{
    		return -1;
    	}
    	
    	if (maand > datum2.maand)
    	{
    		return 1;
    	}
    	else if (maand < datum2.maand)
    	{
    		return -1;
    	}
    	
    	if (dag > datum2.dag)
    	{
    		return 1;
    	}
    	else if (dag < datum2.dag)
    	{
    		return -1;
    	}
    	return 0;
    }
    
    /**
     * Geef een string representatie terug van de datum
     * @return Datum in string formaat
     */
    public String toString()
    {
    	return dag + " " + maanden[maand - 1] + " " + jaar;
    }
    
    /**
     * Deze methode kijkt of de gegeven dag geldig is voor het gegeven jaar, rekening
     * houdend met schrikkeljaren. De details van de datum moeten al geset zijn
     * 
     * @return true Als de datum geldig is
     */
    private boolean datumValidatie()
    {
        switch (maand)
        {
            case 2:
            	// 1) Als het geen schrikkeljaar is, heeft februari max 28 dagen
            	// 2) Wel een schrikkeljaar? Max 29 dagen
            	if ((!isSchrikkeljaar(jaar) && dag >= 29) || (isSchrikkeljaar(jaar) && dag > 29))
            	{
            		new Exception("De dag is niet juist voor de gegeven maand februari");
            	}
            	break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dag > 30)
                {
                    new Exception("De dag is niet juist voor de gegeven maand");
                }
                break;
        }
    	return true;
    }
    
    /**
     * 
     * @param jaar
     * @return
     */
    private boolean isSchrikkeljaar(int jaar)
    {
        if (jaar % 400 == 0)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 
     * @param dag
     * @return
     */
    private void setDag(int dag)
    {
    	if (dag < 1 || dag > 31)
        {
            new Exception("Ongeldige dag gegeven");
        }
    	this.dag = dag;
    }
    
    /**
     * 
     * @param maand
     * @return
     */
    private void setMaand(int maand)
    {
    	if (maand < 1 || maand > 12)
        {
            new Exception("Ongeldige maand gegeven");
        }
    	this.maand = maand;
    }
    
    /**
     * 
     * @param jaar
     * @return
     */
    private void setJaar(int jaar)
    {
    	if (jaar < 0)
        {
            new Exception("Ongeldig jaar gegeven");
        }
    	this.jaar = jaar;
    }
    
	
	/**
	 * Return de huidige datum van het systeem
	 * @return Date
	 */
	private void HuidigeSysteemDatum()
	{
		Date Datum = new Date();
		
		setDag(Datum.getDate());
		setMaand(Datum.getMonth());
		setJaar(Datum.getYear());
	}
}
