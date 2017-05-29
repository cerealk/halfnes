package com.grapeshot.halfnes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Logger
{
  private boolean loggingEnabled;
  private OutputStreamWriter loggerStreameWriter;

  public Logger(boolean logging)
  {
    loggingEnabled = logging;
  }

  public void logMessage(String tolog)
  {
    if (isLogging()) {
        try {
          loggerStreameWriter.write(tolog);
        } catch (IOException e) {
            System.err.println("Cannot write to debug log" + e.getLocalizedMessage());
        }
    }
  }

  public void flushLog() {
        if (isLogging()) {
            try {
              loggerStreameWriter.flush();
            } catch (IOException e) {
                System.err.println("Cannot write to debug log" + e.getLocalizedMessage());
            }
        }
    }

  public void startLog(String path) {
      setLogging(true);
      try {
        loggerStreameWriter = new OutputStreamWriter(new FileOutputStream(new File(path)), StandardCharsets.UTF_8);
      } catch (IOException e) {
          System.err.println("Cannot create debug log" + e.getLocalizedMessage());
      }
  }

  public void startLog() {
    startLog("nesdebug.txt");
  }

  public void stopLog() {
        setLogging(false);
        flushLog();
    }

  public boolean isLogging()
  {
    return loggingEnabled;
  }

  public void setLogging(boolean logging)
  {
    loggingEnabled = logging;
  }
}
