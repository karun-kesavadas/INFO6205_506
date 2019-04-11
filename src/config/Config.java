package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton Class that stores the global configuration details of the application
 */
public class Config
{
    private static Config instance;
    private Properties config;

    /**
     * Private Constructor. Loads the user configurable properties.
     */
    private Config()
    {
        config = new Properties();
        try(InputStream input= Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.CONFIGURATION_FILE_PATH);)
        {
            config.load(input);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    };

    /**
     *Returns an instance of the Singleton class Config
     * @return the singleton instance
     */
    public static Config getInstance()
    {
        if(instance==null)
            instance = new Config();
        return instance;
    }

    /**
     * Adds the provided configuration property to the app's global configuration.
     * @param property Configuration property name
     * @param value Configuration property value
     */
    public void setConfig(String property, Object value)
    {
        this.config.put(property,value);
    }

    /**
     * Fetches the value of the requested configuration property. Throws IllegalArgumentException if the property does not exist.
     * @param property Configuration property name
     * @return Configuration property value
     */
    public Object getConfig(String property)
    {
        if(!this.config.containsKey(property))
            throw new IllegalArgumentException("Property does not exist");
        return this.config.get(property);
    }
}
