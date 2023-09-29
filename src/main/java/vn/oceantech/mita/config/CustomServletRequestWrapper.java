package vn.oceantech.mita.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class CustomServletRequestWrapper extends HttpServletRequestWrapper {
    private final HashMap<String, String[]> params;

    public CustomServletRequestWrapper(HttpServletRequest request, HashMap<String, String[]> params) {
        super(request);
        this.params = params;
    }

    public String getParameter(String name) {
        return this.params.containsKey(name) ? ((String[])this.params.get(name))[0] : "";
    }

    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    public String[] getParameterValues(String name) {
        return (String[])this.params.get(name);
    }
}