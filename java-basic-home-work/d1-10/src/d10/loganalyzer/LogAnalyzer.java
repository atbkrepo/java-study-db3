package d10.loganalyzer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class LogAnalyzer {

    private InputStream inputStream;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private String logStringPattern;
    private String logDateFormat;
    //1,2 - groups
    private static final String DEFAULT_LOG_PATTERN="\\[(.*)\\] \\\"(.*)\\\"";
    //[07/Mar/2004:16:06:51 -0800]
    private static final String DEFAULT_LOG_DATE_FORMAT = "dd/MMM/yyyy:HH:mm:ss Z";


    public LogAnalyzer(InputStream inputStream, LocalDateTime timeFrom, LocalDateTime timeTo) {
        this(inputStream,timeFrom,timeTo,DEFAULT_LOG_PATTERN, DEFAULT_LOG_DATE_FORMAT);
    }

    public LogAnalyzer(InputStream inputStream, LocalDateTime timeFrom, LocalDateTime timeTo, String logStringPattern, String logDateFormat) {

        this.inputStream = inputStream;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.logStringPattern = logStringPattern;
        this.logDateFormat = logDateFormat;
    }

    public String getLogStringPattern() {
        return logStringPattern;
    }

    public void setLogStringPattern(String logStringPattern) {
        this.logStringPattern = logStringPattern;
    }

    public String getLogDateFormat() {
        return logDateFormat;
    }

    public void setLogDateFormat(String logDateFormat) {
        this.logDateFormat = logDateFormat;
    }

    public  List<LogToken> analyze() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        List<LogToken> logTokens = new ArrayList<>(100);

        String line;
        Pattern pattern = Pattern.compile(DEFAULT_LOG_PATTERN);
        while ((line = bufferedReader.readLine()) != null) {

            Matcher matcher = pattern.matcher(line);
            matcher.find();

            //System.out.println(s);
            LocalDateTime logTime;
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DEFAULT_LOG_DATE_FORMAT);
            try {
                ZonedDateTime logTimeZoned = ZonedDateTime.parse(matcher.group(1), dateFormat);
                logTime = logTimeZoned.toLocalDateTime();
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Date format is wrong [param 3]. Should be:" + dateFormat.toString());
            }
            if (logTime.isAfter(timeFrom)&&logTime.isBefore(timeTo)){
                String[] tokenParts = matcher.group(2).split("[ ]");
                logTokens.add(new LogToken(HttpMethod.valueOf(tokenParts[0]),logTime,tokenParts[1]));
            }

        }
        return logTokens;
    }
//2019-01-23T00:38:37.038
    public static void main(String[] args) throws IOException {
        System.out.println("===>Now:"+LocalDateTime.now());
        if (args.length != 3) {
            throw new IllegalArgumentException("Params count should be 3");
        }
        if (args[0].isEmpty()) {
            throw new IllegalArgumentException("Path should be not empty [param 1]");
        }
        File file = new File(args[0]);
        InputStream inputStream;
        if (file.exists()) {
            inputStream = new FileInputStream(file);
        } else {
            throw new IllegalArgumentException("File Not exists under the path specified [param 1]:" + args[0]);
        }
        LocalDateTime timeFrom;
        try {
            timeFrom = LocalDateTime.parse(args[1], ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date format is wrong [param 2]. Should be:" + ISO_LOCAL_DATE_TIME.toString());
        }

        LocalDateTime timeTo;
        try {
            timeTo = LocalDateTime.parse(args[2], ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date format is wrong [param 3]. Should be:" + ISO_LOCAL_DATE_TIME.toString());
        }

        LogAnalyzer logAnalyzer = new LogAnalyzer(inputStream, timeFrom, timeTo);
        List<LogToken> analyze = logAnalyzer.analyze();
        System.out.println("===>Size: "+analyze.size());
        System.out.println(analyze);
    }


    private static class LogToken{
        HttpMethod httpMethod;
        LocalDateTime dateTime;
        String message;

        public LogToken(HttpMethod httpMethod, LocalDateTime dateTime, String message) {
            this.httpMethod = httpMethod;
            this.dateTime = dateTime;
            this.message = message;
        }

        @Override
        public String toString() {
            return "LogToken{" +
                    "httpMethod=" + httpMethod +
                    ", dateTime=" + dateTime +
                    ", message='" + message  +
                    '}'+ '\n';
        }
    }
}
