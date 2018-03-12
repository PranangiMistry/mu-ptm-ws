package com.integ.ptm;

import java.util.List;
import java.util.Map;

public interface DBService {
    public Object insert(Object obj);
    public Object update(Object obj);
    public Object delete(Object obj);
    public List<Map<String, Object>> retrieve();
    public List<Map<String, Object>> retrieveById(String obj);

}
