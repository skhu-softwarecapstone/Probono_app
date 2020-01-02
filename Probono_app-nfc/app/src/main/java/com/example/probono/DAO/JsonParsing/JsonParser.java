package com.example.probono.DAO.JsonParsing;

import org.json.JSONObject;

public interface JsonParser<DTO> {
    public DTO parse(String json);


}
