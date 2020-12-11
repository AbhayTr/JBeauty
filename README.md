# JBeauty

Java module for beautifully varying RGB colour sets which can be used for setting colours anywhere!
This module basically varies RGB values in various random ranges via a thread executing continously after very
small interval of time i.e. in milliseconds hence resulting in beautiful transition of colours.

## Installation

Simply include the **JBeauty.jar** file in your project.

### Special Guide For Android Projects:

- Create **libs** folder in your **app** folder where your **app level build.gradle** file is present.
- Download **JBeauty.jar** from here, and place it in the **libs** folder created in previous step.
- In your **app level build.gradle** file, add the following line in **dependencies block**:

```gradle
implementation fileTree(dir: "libs", include: ["*.jar"])
```

- Done!

## Usage

Simply use this code (Modify according to your needs):

```java
import com.abhaytr.jbeauty.Beauty;
import com.abhaytr.jbeauty.ColorListener;
import java.util.Map;
import java.util.HashMap;

class YourClass
{
  
  public static void main(String args[])
  {
    Map parameters = new HashMap(); //For optional parameters (More about them below).
    Beauty.start(parameters, new ColorListener()
    {
      @Override
      public void on_new_color(int[] rgb_color)
      {
        int red_value = rgb_color[0];
        int green_value = rgb_color[1];
        int blue_value = rgb_color[2];
        //Now you can do anything with the RGB values (set as background, font color, etc..).
      }
    });
  }

}
```
Optional Parameters that are available are listed below in Parameters section and have to be passed as a Map (keys as name of parameters listed in Parameters section and value is your desired choice according to the options available for that parameter as specified in the Parameters section) which will be the first parameter for start() method of Beauty class.

## Parameters
- ### mode (Optional)
  
  Specifies colour set in which the colours have to vary.
  
  - **Parameter Value Type:** String or int[].
  
  - **Options for mode parameter**
    - **"dark":** Varies the colour in dark colours only (useful for dark mode projects).
    - **"light":** Varies the colour in light colours only (useful for light mode projects).
    - **[start_rgb, end_rgb]:** Varies the colour from start_rgb value (can be from 0 to 255) to end_rgb value (can be from 0 to 255).
  
  - **Default Value:** "" (i.e. varies from 0 to 255 RGB Values).

- ### start (Optional):
  
  Specefies colour in RGB format from which colours have to start varying.
  
  - **Input type for start parameter:** int[]{R_Value, G_Value, B_Value} (eg. int[]{0, 0, 0} for black).
  
  - **Default Value:** int[]{0, 0, 0} (for "dark", none or other mode parameter specified) and int[]{255, 255, 255} (for "light" mode parameter specified).

- ### time (Optional):
  
  Specifies the time in milliseconds after which the colour is changed according to its range.
  Useful for decreasing the time when using on slow hardware for maintaining the smoothness.
  
  - **Parameter Value Type:** int.
  
  - **Default Value:** 40 ms (Just perfect for majority hardware types).
  
So go ahead and enjoy the beauty of time varying RGB colour sets!
