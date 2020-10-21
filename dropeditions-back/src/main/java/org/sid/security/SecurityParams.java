package org.sid.security;

public interface SecurityParams {
  public static final String JWT_HEADER_NAME = "Authorization";
  public static final String SECRET = "med@jaouad.net";
  public static final long EXPERATION = 10*24*3600*1000;
  public static final String HEADER_PREFIX = "Bearer ";
}
