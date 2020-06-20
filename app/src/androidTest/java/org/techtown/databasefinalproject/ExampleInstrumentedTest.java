package org.techtown.databasefinalproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("org.techtown.databasefinalproject", appContext.getPackageName());
        SqlManager sqlManager = new SqlManager();
        sqlManager.createDatabase(appContext);
        String testName =  sqlManager.executeQuery("SELECT * from Plant WHERE plant_name like \"%" + "꽃" + "%\"" ).get(0).getName();
        assertEquals(testName, "개싸리");

    }
}
