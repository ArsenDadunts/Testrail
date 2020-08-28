/**
 * TestRail API binding for Java (API v2, available since TestRail 3.0)
 * Updated for TestRail 5.7
 * <p>
 * Learn more:
 * <p>
 * http://docs.gurock.com/testrail-api2/start
 * http://docs.gurock.com/testrail-api2/accessing
 * <p>
 * Copyright Gurock Software GmbH. See license.md for details.
 */

package testrail;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static testrail.utils.Utils_Constants.convertToArray;
import static testrail.utils.Utils_Constants.convertToObject;


public class APIClient {
    private String m_user;
    private String m_password;
    private String m_url;
    public Response response;

    public APIClient(String base_url) {
        if (!base_url.endsWith("/")) {
            base_url += "/";
        }

        this.m_url = base_url + "index.php?/api/v2/";
    }

    public String getUser() {
        return this.m_user;
    }

    public void setUser(String user) {
        this.m_user = user;
    }

    public String getPassword() {
        return this.m_password;
    }

    public void setPassword(String password) {
        this.m_password = password;
    }

    public Object sendGet(String uri, String data, int statusCode) {
        JSONObject response = null;
        try {
            response = (JSONObject) this.sendRequest("GET", uri, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object sendGet(String uri, int statusCode) {
        Object response = null;
        try {
            response = this.sendRequest("GET", uri, null, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object sendPost(String uri, Object data, int statusCode) {
        JSONObject response = null;
        try {
            response = (JSONObject) this.sendRequest("POST", uri, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    private Object sendRequest(String method, String url, Object data, int statusCode)
            throws MalformedURLException, IOException, APIException {
        if (method.equals("POST")) {
            if (data != null) {
                if (url.startsWith("add_attachment"))   // add_attachment API requests
                {
                    RequestSpecBuilder specBuilder = new RequestSpecBuilder();
                    String boundary = "TestRailAPIAttachmentBoundary"; //Can be any random string
                    File uploadFile = new File((String) data);
                    response = given().log().method().log().uri().log().headers().log().body()
                            .header("Authorization", "Basic " + getAuthorization(this.m_user, this.m_password))
                            .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                            .spec(specBuilder.build())
                            .multiPart(uploadFile)
                            .post(this.m_url + url);
                    System.out.println("Response:");
                    response.prettyPrint();
                    assertEquals(response.statusCode(), statusCode);
                    return convertToObject(response);
                } else {
                    Object res = new Gson().toJson(data);
                    response = given().log().method().log().uri().log().headers().log().body()
                            .header("Authorization", "Basic " + getAuthorization(this.m_user, this.m_password))
                            .header("Content-Type", "application/json")
                            .body(res)
                            .post(this.m_url + url);
                    System.out.println("Response:");
                    response.prettyPrint();
                    assertEquals(response.statusCode(), statusCode);
                    return convertToObject(response);
                }
            } else {
                response = given().log().method().log().uri().log().headers().log().body()
                        .header("Authorization", "Basic " + getAuthorization(this.m_user, this.m_password))
                        .header("Content-Type", "application/json")
                        .post(this.m_url + url);
                response.getBody().prettyPrint();
                System.out.println("Response:");
                response.prettyPrint();
                assertEquals(response.statusCode(), statusCode);
                return convertToObject(response);
            }
        } else if (method.equals("GET")) {
            response = given().log().method().log().uri().log().headers().log().body()
                    .header("Authorization", "Basic " + getAuthorization(this.m_user, this.m_password))
                    .header("Content-Type", "application/json")
                    .get(this.m_url + url);
            System.out.println("Response:");
            response.prettyPrint();
            assertEquals(response.statusCode(), statusCode);
            if (response.body().asString().startsWith("[")){
                return convertToArray(response);
            }else {
                return convertToObject(response);
            }
        }

        int status = response.statusCode();

        InputStream istream;
        if (status != 200) {
            istream = response.asInputStream();
            if (istream == null) {
                throw new APIException(
                        "TestRail API return HTTP " + status +
                                " (No additional error message received)"
                );
            }
        } else {
            istream = response.asInputStream();
        }// If 'get_attachment' (not 'get_attachments') returned valid status code, save the file
        if ((istream != null)
                && (url.startsWith("get_attachment/"))) {
            FileOutputStream outputStream = new FileOutputStream((String) data);

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = istream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            istream.close();
            return (String) data;
        }

        String text = "";
        if (istream != null) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            istream,
                            "UTF-8"
                    )
            );

            String line;
            while ((line = reader.readLine()) != null) {
                text += line;
                text += System.getProperty("line.separator");
            }

            reader.close();
        }

        Object result;
        if (!text.equals("")) {
            result = JSONValue.parse(text);
        } else {
            result = new JSONObject();
        }

        if (status != 200) {
            String error = "No additional error message received";
            if (result != null && result instanceof JSONObject) {
                JSONObject obj = (JSONObject) result;
                if (obj.containsKey("error")) {
                    error = '"' + (String) obj.get("error") + '"';
                }
            }

            throw new APIException(
                    "TestRail API returned HTTP " + status +
                            "(" + error + ")"
            );
        }

        return result;
    }

    private static String getAuthorization(String user, String password) {
        try {
            return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
        } catch (IllegalArgumentException e) {
            // Not thrown
        }

        return "";
    }
}
