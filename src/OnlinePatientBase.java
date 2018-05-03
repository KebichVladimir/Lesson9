import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OnlinePatientBase {
    private List<Patient> basePatientOnline = new ArrayList<>();

    public List<Patient> getOnlinePatientBase() throws IOException, ParseException {
        URL myUrl = new URL("https://raw.githubusercontent.com/KebichVladimir/Files/master/OnlinePatientBase.txt");
        HttpURLConnection myUrlCon = (HttpURLConnection) myUrl.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(myUrlCon.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            String[] value = inputLine.split(";");
            String firstName = value[0];
            String secondName = value[1];
            SimpleDateFormat sdf = new SimpleDateFormat(("yyyy-MM-dd"));
            Date birthday = sdf.parse(value[2]);
            boolean healthy = Boolean.valueOf(value[3]);
            basePatientOnline.add(new Patient(firstName, secondName, birthday, healthy));
        }
        return basePatientOnline;
    }
}