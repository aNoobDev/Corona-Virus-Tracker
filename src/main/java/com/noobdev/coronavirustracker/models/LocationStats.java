package com.noobdev.coronavirustracker.models;

public class LocationStats {

    private String state;
    private String confirmed;
    private String recovered;
    private String deaths;
    private String active;
    private String date;
  
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confimred) {
		this.confirmed = confimred;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getDeaths() {
		return deaths;
	}

	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	

    
}
