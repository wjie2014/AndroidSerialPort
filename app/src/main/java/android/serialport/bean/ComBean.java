package android.serialport.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ComBean
{
  public byte[] bRec = null;
  public String sComPort = "";
  public String sRecTime = "";
  
  public ComBean(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    this.sComPort = paramString;
    this.bRec = new byte[paramInt];
    int i = 0;
    for (;;)
    {
      if (i >= paramInt)
      {
        this.sRecTime = new SimpleDateFormat("hh:mm:ss").format(new Date());
        return;
      }
      this.bRec[i] = paramArrayOfByte[i];
      i += 1;
    }
  }
}


/* Location:              C:\Users\JP\Desktop\安卓开发必备\安卓反编译\代码\dex2jar-2.0\classes-dex2jar.jar!\com\bjw\bean\ComBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */