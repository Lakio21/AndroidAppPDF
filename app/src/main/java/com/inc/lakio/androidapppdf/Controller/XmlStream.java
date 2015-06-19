package com.inc.lakio.androidapppdf.Controller;

import android.content.Context;
import android.renderscript.ScriptIntrinsicYuvToRGB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

/**
 * Created by Lakio on 15/06/2015.
 */
public class XmlStream
{

    public String getPlanningInfo(String _fileName,Context ctx)
    {
        BufferedReader input = null;
        String result ="";
        try {
            input = new BufferedReader(new InputStreamReader(ctx.openFileInput(_fileName)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public Boolean setPlanningInfo(String outputText,String _fileName,Context ctx)
    {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(ctx.openFileOutput(_fileName, ctx.MODE_PRIVATE)));
            writer.write(outputText);
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        finally
        {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
