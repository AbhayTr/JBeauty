package com.abhaytr.jbeauty;

/*

Welcome to JBeauty

Java Module for beautifully varying RGB colour sets
that can be set as backgrounds anywhere!

For usgae, kindly read the instructions in README.md
available at https://github.com/AbhayTr/JBeauty.

Optional Parameters that are available are listed below
in @params and have to be passed as a Map (keys as name of
parameters listed in @params and value is your desired choice
according to the options available for that parameter as
specified in @params) which will be the
first parameter for start() method of Beauty class.

@params

mode (Optional):

  Specifies colour set in which the colours have to vary.

  Parameter Value Type: String or int[].

  Options for mode parameter:
    "dark": Varies the colour in dark colours only (useful for dark mode projects).
    "light": Varies the colour in light colours only (useful for light mode projects).
    [start_rgb, end_rgb]: Varies the colour from start_rgb value (can be from 0 to 255) to end_rgb value (can be from 0 to 255).

  Default Value: "" (i.e. varies from 0 to 255 RGB Values).

start (Optional):

  Specefies colour in RGB format from which colours have to start varying.

  Input type for start parameter: int[]{R_Value, G_Value, B_Value} (eg. int[]{0, 0, 0} for black).

  Default Value: int[]{0, 0, 0} (for "dark", none or other mode parameter specified) and int[]{255, 255, 255} (for "light" mode parameter specified).

time (Optional):

  Specifies the time in milliseconds after which the colour is changed according to its range.
  Useful for decreasing the time when using on slow hardware for maintaining the smoothness.

  Parameter Value Type: int.

  Default Value: 40 ms (Just perfect for majority hardware types).

So go ahead and enjoy the beauty of time varying RGB colour sets!

© Abhay Tripathi

*/

import java.util.*;
import java.util.concurrent.*;
import com.abhaytr.jbeauty.*;

public class Beauty
{

    public static int red = 0;
    public static int green = 0;
    public static int blue = 0;
    public static int red_range = 0;
    public static int green_range = 0;
    public static int blue_range = 0;
    public static int terminal_red = 0;
    public static int terminal_green = 0;
    public static int terminal_blue = 0;
    public static int min = 0;
    public static int max = 255;
    public static int[] current_rgb_values = {red, green, blue};

    public static void start(Map<String, Object> parameters, final ColorListener set_color_to)
    {
        int time = 40;
        Object mode = "";
        int start_bg_color[] = {0, 0, 0};
        if (parameters != null)
        {
            if (parameters.containsKey("mode"))
            {
                mode = parameters.get("mode");
            }
            if (parameters.containsKey("start"))
            {
                start_bg_color = (int[]) parameters.get("start");
            }
            if (parameters.containsKey("speed"))
            {
                time = (int) parameters.get("speed");
                if (time < 1)
                {
                    time = 40;
                }
            }
        }
        int permissible_range_undecided = 100;
        if (!mode.equals(""))
        {
            permissible_range_undecided = 30;
        }
        final int permissible_range = permissible_range_undecided;
        if (mode.equals("dark"))
        {
            max = 140;
        }
        else if (mode.equals("light"))
        {
            min = 140;
        }
        else if (!mode.equals(""))
        {
            if (((int[]) mode).length == 2)
            {
                min = ((int[]) mode)[0];
                max = ((int[]) mode)[1];
                if (min < 0)
                {
                    min = 0;
                }
                if (max > 255)
                {
                    max = 255;
                }
            }
        }
        if (mode.equals("light"))
        {
            red = 255;
            green = 255;
            blue = 255;
        }
        if (start_bg_color[0] != 0 || start_bg_color[1] != 0 || start_bg_color[2] != 0)
        {
            if (start_bg_color[0] < min || start_bg_color[1] < min || start_bg_color[2] < min)
            {
                start_bg_color[0] = min;
                start_bg_color[1] = min;
                start_bg_color[2] = min;
            }
            if (start_bg_color[0] > max || start_bg_color[1] > max || start_bg_color[2] > max)
            {
                start_bg_color[0] = max;
                start_bg_color[1] = max;
                start_bg_color[2] = max;
            }
            red = start_bg_color[0];
            green = start_bg_color[1];
            blue = start_bg_color[2];
        }
        current_rgb_values[0] = red;
        current_rgb_values[1] = green;
        current_rgb_values[2] = blue;
        set_color_to.on_new_color(current_rgb_values);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                if (red_range == 0 && green_range == 0 && blue_range == 0)
                {
                    red_range = ThreadLocalRandom.current().nextInt(-permissible_range, permissible_range + 1);
                    green_range = ThreadLocalRandom.current().nextInt(-permissible_range, permissible_range + 1);
                    blue_range = ThreadLocalRandom.current().nextInt(-permissible_range, permissible_range + 1);
                    if
                    (
                            ((red + red_range) >= min && (red + red_range) <= max)
                                    &&
                                    ((green + green_range) >= min && (green + green_range) <= max)
                                    &&
                                    ((blue + blue_range) >= min && (blue + blue_range) <= max)
                                    &&
                                    red_range != 0 && green_range != 0 && blue_range != 0
                    )
                    {
                        terminal_red = red + red_range;
                        terminal_green = green + green_range;
                        terminal_blue = blue + blue_range;
                    }
                    else
                    {
                        red_range = 0;
                        green_range = 0;
                        blue_range = 0;
                    }
                }
                else
                {
                    if (red == terminal_red)
                    {
                        red_range = 0;
                    }
                    else
                    {
                        if (red_range < 0)
                        {
                            red -= 1;
                        }
                        else
                        {
                            red += 1;
                        }
                    }

                    if (green == terminal_green)
                    {
                        green_range = 0;
                    }
                    else
                    {
                        if (green_range < 0)
                        {
                            green -= 1;
                        }
                        else
                        {
                            green += 1;
                        }
                    }

                    if (blue == terminal_blue)
                    {
                        blue_range = 0;
                    }
                    else
                    {
                        if (blue_range < 0)
                        {
                            blue -= 1;
                        }
                        else
                        {
                            blue += 1;
                        }
                    }
                }
                current_rgb_values[0] = red;
                current_rgb_values[1] = green;
                current_rgb_values[2] = blue;
                set_color_to.on_new_color(current_rgb_values);
            }
        }, 0, time, TimeUnit.MILLISECONDS);
    }
}