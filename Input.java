import java.io.Closeable;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input implements Closeable, Iterator<String>
{
    protected Scanner scanner;
    public Input()
    {
        this(System.in);
    }
    public Input(final InputStream in)
    {
        scanner = new Scanner(in);
    }

    public void close()
    {
        scanner.close();
    }

    public boolean hasNext()
    {
        boolean returnValue = false;
        try
        {
            returnValue = scanner.hasNext();
        }
        catch (IllegalStateException e)
        {
            illegalStateExceptionHandler();
        }
        return returnValue;
    }

    public String next()
    {
        String returnValue = null;
        try
        {
            returnValue = scanner.next();
        }
        catch (NoSuchElementException nsee)
        {
            noSuchElementHandler();
        }
        catch (IllegalStateException ise)
        {
            illegalStateExceptionHandler();
        }
        return returnValue;
    }

    public void remove()
    {
    }

    public int nextInt()
    {
        int returnValue = 0;
        try
        {
            returnValue = scanner.nextInt();
        }
        catch (InputMismatchException ime)
        {
            inputMismatchExceptionHandler("int");
        }
        catch (NoSuchElementException nsee)
        {
            noSuchElementHandler();
        }
        catch (IllegalStateException ise)
        {
            illegalStateExceptionHandler();
        }
        return returnValue;
    }

    public String nextLine()
    {
        String returnValue = null;
        try
        {
            returnValue = scanner.nextLine();
        }
        catch (NoSuchElementException nsee)
        {
            noSuchElementHandler();
        }
        catch (IllegalStateException ise)
        {
            illegalStateExceptionHandler();
        }
        return returnValue;
    }

    private void illegalStateExceptionHandler()
    {
        System.err.println("Input has been closed.");
        System.exit(1);
    }

    private void inputMismatchExceptionHandler(final String type)
    {
        System.err.println("Input did not represent " +
                (type.equals("int") ? "an" : "a") + " " + type + " value.");
        System.exit(1);
    }

    private void noSuchElementHandler()
    {
        System.err.println("No input to be read.");
        System.exit(1);
    }
}
