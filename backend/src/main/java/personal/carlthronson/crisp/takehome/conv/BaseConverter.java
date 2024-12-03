package personal.carlthronson.crisp.takehome.conv;

import java.util.Arrays;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import personal.carlthronson.crisp.takehome.entity.BaseEntity;
import personal.carlthronson.crisp.takehome.response.BaseResponse;

public class BaseConverter<ENTITY extends BaseEntity, RESPONSE extends BaseResponse> {

  Logger logger = Logger.getLogger(getClass().getName());

  static ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

  private Class<RESPONSE> responseClass;

  public BaseConverter(Class<RESPONSE> responseClass) {
    this.responseClass = responseClass;
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }

  public RESPONSE convertToResponse(ENTITY in) {
    String json;
    try {
      json = mapper.writeValueAsString(in);
      return mapper.readValue(json, responseClass);
    } catch (JsonProcessingException jpe) {
      throw new IllegalArgumentException(jpe);
    }
  }

  public <E extends Enum<E>> E stringToEnum(Class<E> enumClass, String value) {
    if (value == null) {
      return null;
    }

    return Arrays.stream(enumClass.getEnumConstants()).filter(e -> e.name().equalsIgnoreCase(value)).findFirst()
        .orElse(null);
  }
}
