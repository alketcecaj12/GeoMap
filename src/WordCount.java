import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class WordCount {

	public static String[] countries = {"Afghanistan","Akrotiri","Albania","England","UK","Algeria","American Samoa","Andorra",
		"US","Angola","Anguilla","Antarctica","Antigua and Barbuda","Argentina","Armenia","Aruba","Ashmore and Cartier Islands",
		"Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Bassas da India","Belarus","Belgium","Belize",
		"Benin","Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Bouvet Island","Brazil","British Indian Ocean Territory",
		"British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burma","Burundi","Cambodia","Cameroon","Canada","Cape Verde",
		"Cayman Islands","Central African Republic","Chad","Chile","China","Christmas Island","Clipperton Island","Cocos (Keeling) Islands",
		"Colombia","Comoros","Congo, Democratic Republic of the","Congo, Republic of the","Cook Islands","Coral Sea Islands",
		"Costa Rica","Cote d'Ivoire","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Dhekelia","Djibouti","Dominica",
		"Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia","Europa Island",
		"Falkland Islands (Islas Malvinas)","Faroe Islands","Fiji","Finland","France","French Guiana","French Polynesia",
		"French Southern and Antarctic Lands","Gabon","Gambia, The","Gaza Strip","Georgia","Germany","Ghana","Gibraltar",
		"Glorioso Islands","Greece","Greenland","Grenada","Guadeloupe","Guam","Guatemala","Guernsey","Guinea","Guinea-Bissau",
		"Guyana","Haiti","Heard Island and McDonald Islands","Holy See (Vatican City)","Honduras","Hong Kong","Hungary","Iceland",
		"India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Jan Mayen","Japan","Jersey","Jordan",
		"Juan de Nova Island","Kazakhstan","Kenya","Kiribati","Korea, North","Korea, South","Kuwait","Kyrgyzstan","Laos","Latvia",
		"Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi",
		"Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique","Mauritania","Mauritius","Mayotte","Mexico",
		"Micronesia, Federated States of","Moldova","Monaco","Mongolia","Montserrat","Morocco","Mozambique","Namibia","Nauru",
		"Navassa Island","Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand","Nicaragua","Niger",
		"Nigeria","Niue","Norfolk Island","Northern Mariana Islands","Norway","Oman","Pakistan","Palau","Panama","Papua New Guinea",
		"Paracel Islands","Paraguay","Peru","Philippines","Pitcairn Islands","Poland","Portugal","Puerto Rico","Qatar","Reunion",
		"Romania","Russia","Rwanda","Saint Helena","Saint Kitts and Nevis","Saint Lucia","Saint Pierre and Miquelon",
		"Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia",
		"Montenegro","Seychelles","Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa",
		"South Georgia and the South Sandwich Islands","Spain","Spratly Islands","Sri Lanka","Sudan","Suriname","Svalbard","Swaziland",
		"Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo","Tokelau","Tonga",
		"Trinidad and Tobago","Tromelin Island","Tunisia","Turkey","Turkmenistan","Turks and Caicos Islands","Tuvalu",
		"Uganda","Ukraine","United Arab Emirates","United Kingdom","UK","United States","UAE","Uruguay","Uzbekistan",
		"Vanuatu","Venezuela","Vietnam","Virgin Islands","Wake Island","Wallis and Futuna","West Bank","Western Sahara","Yemen",
		"Zambia","Zimbabwe" };
	
	public static void main (String[] args) throws Exception{
		Scanner in = new Scanner(new File("feedatafromeconomist.txt"));
		Map<String, Integer> map = new HashMap<String, Integer>();
		String word="";
		while (in.hasNext()) {

			//System.out.println(countries[i]);	

			word = in.next();
			for (int i = 0; i < countries.length; i++) {
				String country = countries[i];
				if(country.equals(word)||country.equals(word+",")||country.equals(","+word)||country.equals(word+"'s")){
					// System.out.println(country);
					int freq = (map.get(word) == null) ? 1 : map.get(word) + 1;   // type-safe
					map.put(word, freq);      // autobox int to Integer and upcast, type-check
				}
			}

		}
		print(map, "feedmap.html");
		System.out.println("map file printed");

	}
	
	public static void print(Map<String, Integer>map, String file) throws Exception{
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		out.println("<html>");
		out.println("<head>");
		out.println("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>");
			out.println("<script type=\"text/javascript\">");
		out.println("google.load(\"visualization\", \"1\", {packages:[\"geochart\"]});");
		out.println("google.setOnLoadCallback(drawRegionsMap);");

		out.println(" function drawRegionsMap() {");

		out.println("var data = google.visualization.arrayToDataTable([");
		out.println("['Country', 'Popularity'],");
		for (String c : map.keySet()) {
			out.println("          ['"+c+"', "+map.get(c)+"],");
		}
		out.println("]);");

				out.println("var options = {};");

						out.println("var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));");

								out.println("chart.draw(data, options);");
								out.println("}");
      
	out.println("</script>");
	out.println("</head>");
	out.println("<body>");
	out.println("<div id=\"regions_div\" style=\"width: 900px; height: 500px;\"></div>");
	out.println("</body>");
	out.println("</html>");
		out.close();
		
	}
}
