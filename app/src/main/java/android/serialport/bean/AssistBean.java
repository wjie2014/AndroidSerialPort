package android.serialport.bean;

import java.io.Serializable;

public class AssistBean implements Serializable {
  private static final long serialVersionUID = -5620661009186692227L;
  private String SendHexA = "AA";
  private String SendHexB = "BB";
  private String SendHexC = "CC";
  private String SendHexD = "DD";
  private String SendTxtA = "COMA";
  private String SendTxtB = "COMB";
  private String SendTxtC = "COMC";
  private String SendTxtD = "COMD";
  public int iComDispCount = 1;
  public int iReflashTime = 100;
  private boolean isTxt = true;
  public String sTimeA = "500";
  public String sTimeB = "500";
  public String sTimeC = "500";
  public String sTimeD = "500";
  
  public String getSendA()
  {
    if (this.isTxt) {
      return this.SendTxtA;
    }
    return this.SendHexA;
  }
  
  public String getSendB()
  {
    if (this.isTxt) {
      return this.SendTxtB;
    }
    return this.SendHexB;
  }
  
  public String getSendC()
  {
    if (this.isTxt) {
      return this.SendTxtC;
    }
    return this.SendHexC;
  }
  
  public String getSendD()
  {
    if (this.isTxt) {
      return this.SendTxtD;
    }
    return this.SendHexD;
  }
  
  public boolean isTxt()
  {
    return this.isTxt;
  }
  
  public void setSendA(String paramString)
  {
    if (this.isTxt)
    {
      this.SendTxtA = paramString;
      return;
    }
    this.SendHexA = paramString;
  }
  
  public void setSendB(String paramString)
  {
    if (this.isTxt)
    {
      this.SendTxtB = paramString;
      return;
    }
    this.SendHexB = paramString;
  }
  
  public void setSendC(String paramString)
  {
    if (this.isTxt)
    {
      this.SendTxtC = paramString;
      return;
    }
    this.SendHexC = paramString;
  }
  
  public void setSendD(String paramString)
  {
    if (this.isTxt)
    {
      this.SendTxtD = paramString;
      return;
    }
    this.SendHexD = paramString;
  }
  
  public void setTxtMode(boolean paramBoolean)
  {
    this.isTxt = paramBoolean;
  }
}