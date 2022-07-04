import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;

public class NasaInformation {
    private String copyright;
    private Date date;
    private String explanation;
    private String hdUrl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;

    public NasaInformation(@JsonProperty("copyright") String copyright,
                           @JsonProperty("date") Date date,
                           @JsonProperty("explanation") String explanation,
                           @JsonProperty("hdurl") String hdurl,
                           @JsonProperty("media_type") String media_type,
                           @JsonProperty("service_version") String service_version,
                           @JsonProperty("title") String title,
                           @JsonProperty("url") String url

    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdUrl = hdurl;
        this.mediaType = media_type;
        this.serviceVersion = service_version;
        this.title = title;
        this.url = url;

    }

    public String getCopyright() {
        return copyright;
    }

    public Date getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void saveInformation() throws IOException {
        System.out.println("Starting to download Nasa file, type is:" + mediaType);

        String fileName = Paths.get(new URL(hdUrl).getFile()).getFileName().toString();

        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(hdUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(inputStream.readAllBytes());

        }
        System.out.println("Your file has been saved successfully! ");
        System.out.println("C:\\Users\\Admin\\NasaApi" + fileName);

    }
}
