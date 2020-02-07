package model.vo;

/**
 * Representation of a Service object
 */
public class ServiceVo implements Comparable<ServiceVo> {

	
	
	private String company;
	
	private String dropoff_census_tract;
	
	private String dropoff_centroid_latitude;
	
	private voLocalizacion dropoff_centroid_location;
	
	private String dropoff_centroid_longitude;
	
	private String dropoff_community_area;
	
	private String extras;
	
	private String fare;
	
	private String payment_type;
	
	private String pickup_census_tract;
	
	private String pickup_centroid_latitude;
	
	private voLocalizacion pickup_centroid_location;
	
	private String pickup_centroid_longitude;
	
	private String pickup_community_area;
	
	private String taxi_id;
	
	private String tips;
	
	private String tolls;
	
	private String trip_end_timestamp;
	
	private String trip_id;
	
	private String trip_miles;
	
	private String trip_seconds;
	
	private String trip_start_timestamp;
	
	private String trip_total;
	
	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDropoff_census_tract() {
		return dropoff_census_tract;
	}

	public void setDropoff_census_tract(String dropoff_census_tract) {
		this.dropoff_census_tract = dropoff_census_tract;
	}

	public String getDropoff_centroid_latitude() {
		return dropoff_centroid_latitude;
	}

	public void setDropoff_centroid_latitude(String dropoff_centroid_latitude) {
		this.dropoff_centroid_latitude = dropoff_centroid_latitude;
	}

	public voLocalizacion getDropoff_centroid_location() {
		return dropoff_centroid_location;
	}

	public void setDropoff_centroid_location(voLocalizacion dropoff_centroid_location) {
		this.dropoff_centroid_location = dropoff_centroid_location;
	}

	public String getDropoff_centroid_longitude() {
		return dropoff_centroid_longitude;
	}

	public void setDropoff_centroid_longitude(String dropoff_centroid_longitude) {
		this.dropoff_centroid_longitude = dropoff_centroid_longitude;
	}

	public String getDropoff_community_area() {
		return dropoff_community_area;
	}

	public void setDropoff_community_area(String dropoff_community_area) {
		this.dropoff_community_area = dropoff_community_area;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getPickup_census_tract() {
		return pickup_census_tract;
	}

	public void setPickup_census_tract(String pickup_census_tract) {
		this.pickup_census_tract = pickup_census_tract;
	}

	public String getPickup_centroid_latitude() {
		return pickup_centroid_latitude;
	}

	public void setPickup_centroid_latitude(String pickup_centroid_latitude) {
		this.pickup_centroid_latitude = pickup_centroid_latitude;
	}

	public voLocalizacion getPickup_centroid_location() {
		return pickup_centroid_location;
	}

	public void setPickup_centroid_location(voLocalizacion pickup_centroid_location) {
		this.pickup_centroid_location = pickup_centroid_location;
	}

	public String getPickup_centroid_longitude() {
		return pickup_centroid_longitude;
	}

	public void setPickup_centroid_longitude(String pickup_centroid_longitude) {
		this.pickup_centroid_longitude = pickup_centroid_longitude;
	}

	public String getPickup_community_area() {
		return pickup_community_area;
	}

	public void setPickup_community_area(String pickup_community_area) {
		this.pickup_community_area = pickup_community_area;
	}

	public String getTaxi_id() {
		return taxi_id;
	}

	public void setTaxi_id(String taxi_id) {
		this.taxi_id = taxi_id;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getTolls() {
		return tolls;
	}

	public void setTolls(String tolls) {
		this.tolls = tolls;
	}

	public String getTrip_end_timestamp() {
		return trip_end_timestamp;
	}

	public void setTrip_end_timestamp(String trip_end_timestamp) {
		this.trip_end_timestamp = trip_end_timestamp;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getTrip_miles() {
		return trip_miles;
	}

	public void setTrip_miles(String trip_miles) {
		this.trip_miles = trip_miles;
	}

	public String getTrip_seconds() {
		return trip_seconds;
	}

	public void setTrip_seconds(String trip_seconds) {
		this.trip_seconds = trip_seconds;
	}

	public String getTrip_start_timestamp() {
		return trip_start_timestamp;
	}

	public void setTrip_start_timestamp(String trip_start_timestamp) {
		this.trip_start_timestamp = trip_start_timestamp;
	}

	public String getTrip_total() {
		return trip_total;
	}

	public void setTrip_total(String trip_total) {
		this.trip_total = trip_total;
	}

	
	
	public enum Comparador
	{
		
	}
	private Comparador criterio;
	public void cambiarCriterio(Comparador crit)
	{
		criterio =crit;
	}
	@Override
	public int compareTo(ServiceVo arg0) {
		return 0;
	}

	
	
	
	

}
