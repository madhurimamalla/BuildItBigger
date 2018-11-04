package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class TestFreeJokeButton {

    private static final String PACKAGE = "com.udacity.gradle.builditbigger.free";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "Build It Bigger";

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {

        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        mDevice.pressHome();

        final String launcherPackage = getLauncherPackageName();

        assertThat(launcherPackage, notNullValue());

        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), LAUNCH_TIMEOUT);

    }

    @Test
    public void checkPreconditions() {
        assertThat(mDevice, notNullValue());
    }


    @Test
    public void testJokeButtonAndAsyncResponse() {

        mDevice.findObject(By.res(PACKAGE, "joke_button")).isEnabled();
        mDevice.findObject(By.res(PACKAGE, "joke_button")).click();

        try {
            String jokeStr = mDevice.wait(Until.findObject(By.res(PACKAGE, "joke_content")), 500).getText();
            if (jokeStr.equals("dummy_text")) {
                Assert.fail("Check if the server is up and endpoint is reachable");
            }
            assert true;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Check if the server is up and endpoint is reachable");
        }


    }

    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getInstrumentation().getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

}
