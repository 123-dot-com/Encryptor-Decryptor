import java.util.*;  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;

public class Main
{  
    public String input, output;  
    public Main()  //Constructor to initialise data members
    {  
        input = "";  
        output = "";  
    }  

    public void encryptBinary()  //Method to convert from string to binary
    {  
        output = "";
        //Converting input string to binary  
        int ascii = 0, binary_digit = 0, multiplier = 1;  
        String binary = "", binary_rev = ""; 
        for (int counter = 0; counter < input.length(); counter++)  
        {  
            ascii = (int)(input.charAt(counter));  
            multiplier = 1;  
            binary = "";  
            binary_rev = ""; 
            while (ascii != 0)  
            {  
                binary_digit = ascii % 2;  
                ascii /= 2;  
                binary_rev += binary_digit;  
                multiplier *= 10;  
            }  

            for(int counter_inner = binary_rev.length() - 1; counter_inner >= 0; counter_inner--) 
            { 
                binary += binary_rev.charAt(counter_inner); 
            } 
            output += (binary+"-");  
        }
    }  

    public void encryptAscii()  //Method to convert from string to ascii
    {  
        output = "";
        //Converting input string to ASCII  
        char letter;  
        for (int counter = 0; counter < input.length(); counter++)  
        {  
            letter = input.charAt(counter);  
            output += ((int)letter+"-");  
        } 
    }  

    public void encryptToggle() //If letter < m, it will be upper case and add by 2, if it is >= m, it will be lower case and subtracted by 2  
    {  
        output = "";
        //Converting input string to toggle  
        char letter;  
        input = input.toLowerCase();  
        for (int counter = 0; counter < input.length(); counter++)  
        {  
            letter = input.charAt(counter);  
            if (letter < 'm')  
            {  
                output += (char)(Character.toUpperCase(letter) + 2);  
            }  
            else  
            {  
                output += (char)(Character.toLowerCase(letter) - 2);  
            }  
        }  
    }  

    public void decryptBinary() //Method to convert from binary to string
    { 
        output = "";
        int power = 0, binary_num = 0, index = 0, digit = 0, sum = 0; 
        for (int counter = 0; counter < input.length(); counter++) 
        { 
            if (input.charAt(counter) == '-')
            {
                power = 0;
                sum = 0;
                binary_num = Integer.parseInt(input.substring(index, counter));
                index = counter + 1;
                while (binary_num != 0)
                {
                    digit = binary_num % 10;
                    sum += (digit * Math.pow(2, power));
                    power++;
                    binary_num /= 10;
                }
                output += (char)sum;
            }
        } 
    } 

    public void decryptAscii() //Method to convert from ascii to string
    {  
        output = "";
        int ascii = 0, index = 0; 
        char letter; 
        for (int counter = 0; counter < input.length(); counter++) 
        { 
            letter = input.charAt(counter); 
            if (letter == '-') 
            { 
                ascii = Integer.parseInt(input.substring(index, counter)); 
                index = counter + 1;
                output += (char)ascii; 
            } 
        } 
    } 

    public void decryptToggle() //Method to convert from toggle to string
    { 
        output = "";
        char letter;
        for (int counter = 0; counter < input.length(); counter++)
        {
            letter = input.charAt(counter);
            if (Character.isLowerCase(letter))
            {
                output += (char)(letter + 2);
            }
            else
            {
                output += (char)(Character.toLowerCase(letter)-2);
            }
        }
    } 

    public void frameCreate()
    {
        JFrame frame = new JFrame("Encryptor and Decryptor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Main Tab
        JTabbedPane main_tab = new JTabbedPane();

        
        //Encrypt Tab
        JTabbedPane encrypt_tab = new JTabbedPane();
        JPanel encrypt_panel = new JPanel();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets (25, 25, 20, 20);

        
        
        //String to Binary Tab
        JPanel string_to_binary = new JPanel();
        string_to_binary.setLayout(new GridBagLayout());
        ImageIcon S_to_B_image = new ImageIcon("S to B.png");
        JLabel S_to_B_label = new JLabel(S_to_B_image, 0);
        constraints.gridx = 1;
        constraints.gridy = 0;  
        string_to_binary.add(S_to_B_label, constraints);

        JLabel S_to_B_input_text = new JLabel("Input -");
        S_to_B_input_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        string_to_binary.add(S_to_B_input_text, constraints);

        JTextArea S_to_B_input = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 1;
        string_to_binary.add(S_to_B_input, constraints);

        JLabel S_to_B_output_text = new JLabel("Output -");
        S_to_B_output_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        string_to_binary.add(S_to_B_output_text, constraints);

        JTextArea S_to_B_output = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 2;
        string_to_binary.add(S_to_B_output, constraints);

        JButton S_to_B_button = new JButton("Enter");
        S_to_B_button.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 3;
        string_to_binary.add(S_to_B_button, constraints);
        S_to_B_button.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    input = S_to_B_input.getText();
                    encryptBinary();
                    S_to_B_output.setText(output);
                }
            });

        JButton S_to_B_clear = new JButton("Clear");
        S_to_B_clear.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 4;
        string_to_binary.add(S_to_B_clear, constraints);
        S_to_B_clear.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    S_to_B_input.setText("");
                    S_to_B_output.setText("");
                }
            });

        encrypt_tab.add(string_to_binary, "String to Binary");
        

        
        //String to ASCII Tab
        JPanel string_to_ascii = new JPanel();
        string_to_ascii.setLayout(new GridBagLayout());
        ImageIcon S_to_A_image = new ImageIcon("S to A.png");
        JLabel S_to_A_label = new JLabel(S_to_A_image, 0);
        constraints.gridx = 1;
        constraints.gridy = 0;  
        string_to_ascii.add(S_to_A_label, constraints);

        JLabel S_to_A_input_text = new JLabel("Input -");
        S_to_A_input_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        string_to_ascii.add(S_to_A_input_text, constraints);

        JTextArea S_to_A_input = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 1;
        string_to_ascii.add(S_to_A_input, constraints);

        JLabel S_to_A_output_text = new JLabel("Output -");
        S_to_A_output_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        string_to_ascii.add(S_to_A_output_text, constraints);

        JTextArea S_to_A_output = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 2;
        string_to_ascii.add(S_to_A_output, constraints);

        JButton S_to_A_button = new JButton("Enter");
        S_to_A_button.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 3;
        string_to_ascii.add(S_to_A_button, constraints);
        S_to_A_button.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    input = S_to_A_input.getText();
                    encryptAscii();
                    S_to_A_output.setText(output);
                }
            });

        JButton S_to_A_clear = new JButton("Clear");
        S_to_A_clear.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 4;
        string_to_ascii.add(S_to_A_clear, constraints);
        S_to_A_clear.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    S_to_A_input.setText("");
                    S_to_A_output.setText("");
                }
            });
        encrypt_tab.add(string_to_ascii, "String to Ascii");
        
        
        
        //String to Toggle Tab
        JPanel string_to_toggle = new JPanel();
        string_to_toggle.setLayout(new GridBagLayout());
        ImageIcon S_to_T_image = new ImageIcon("S to T.png");
        JLabel S_to_T_label = new JLabel(S_to_T_image, 0);
        constraints.gridx = 1;
        constraints.gridy = 0;  
        string_to_toggle.add(S_to_T_label, constraints);

        JLabel S_to_T_input_text = new JLabel("Input -");
        S_to_T_input_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        string_to_toggle.add(S_to_T_input_text, constraints);

        JTextArea S_to_T_input = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 1;
        string_to_toggle.add(S_to_T_input, constraints);

        JLabel S_to_T_output_text = new JLabel("Output -");
        S_to_T_output_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        string_to_toggle.add(S_to_T_output_text, constraints);

        JTextArea S_to_T_output = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 2;
        string_to_toggle.add(S_to_T_output, constraints);

        JButton S_to_T_button = new JButton("Enter");
        S_to_T_button.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 3;
        string_to_toggle.add(S_to_T_button, constraints);
        S_to_T_button.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    input = S_to_T_input.getText();
                    encryptToggle();
                    S_to_T_output.setText(output);
                }
            });

        JButton S_to_T_clear = new JButton("Clear");
        S_to_T_clear.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 4;
        string_to_toggle.add(S_to_T_clear, constraints);
        S_to_T_clear.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    S_to_T_input.setText("");
                    S_to_T_output.setText("");
                }
            });
        encrypt_tab.add(string_to_toggle, "String to Toggle");
        
        
        
        //Decrypt Tab
        JTabbedPane decrypt_tab = new JTabbedPane();
        JPanel decrypt_panel = new JPanel();

        
        
        //Binary to String Tab
        JPanel binary_to_string = new JPanel();
        binary_to_string.setLayout(new GridBagLayout());
        ImageIcon B_to_S_image = new ImageIcon("B to S.png");
        JLabel B_to_S_label = new JLabel(B_to_S_image, 0);
        constraints.gridx = 1;
        constraints.gridy = 0;  
        binary_to_string.add(B_to_S_label, constraints);

        JLabel B_to_S_input_text = new JLabel("Input -");
        B_to_S_input_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        binary_to_string.add(B_to_S_input_text, constraints);

        JTextArea B_to_S_input = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 1;
        binary_to_string.add(B_to_S_input, constraints);

        JLabel B_to_S_output_text = new JLabel("Output -");
        B_to_S_output_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        binary_to_string.add(B_to_S_output_text, constraints);

        JTextArea B_to_S_output = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 2;
        binary_to_string.add(B_to_S_output, constraints);

        JButton B_to_S_button = new JButton("Enter");
        B_to_S_button.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 3;
        binary_to_string.add(B_to_S_button, constraints);
        B_to_S_button.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    input = B_to_S_input.getText();
                    decryptBinary();
                    B_to_S_output.setText(output);
                }
            });

        JButton B_to_S_clear = new JButton("Clear");
        B_to_S_clear.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 4;
        binary_to_string.add(B_to_S_clear, constraints);
        B_to_S_clear.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    B_to_S_input.setText("");
                    B_to_S_output.setText("");
                }
            });

        decrypt_tab.add(binary_to_string, "Binary to String");
        

        
        //Ascii to String Tab
        JPanel ascii_to_string = new JPanel();
        ascii_to_string.setLayout(new GridBagLayout());
        ImageIcon A_to_S_image = new ImageIcon("A to S.png");
        JLabel A_to_S_label = new JLabel(A_to_S_image, 0);
        constraints.gridx = 1;
        constraints.gridy = 0;  
        ascii_to_string.add(A_to_S_label, constraints);

        JLabel A_to_S_input_text = new JLabel("Input -");
        A_to_S_input_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        ascii_to_string.add(A_to_S_input_text, constraints);

        JTextArea A_to_S_input = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 1;
        ascii_to_string.add(A_to_S_input, constraints);

        JLabel A_to_S_output_text = new JLabel("Output -");
        A_to_S_output_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        ascii_to_string.add(A_to_S_output_text, constraints);

        JTextArea A_to_S_output = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 2;
        ascii_to_string.add(A_to_S_output, constraints);

        JButton A_to_S_button = new JButton("Enter");
        A_to_S_button.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 3;
        ascii_to_string.add(A_to_S_button, constraints);
        A_to_S_button.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    input = A_to_S_input.getText();
                    decryptAscii();
                    A_to_S_output.setText(output);
                }
            });

        JButton A_to_S_clear = new JButton("Clear");
        A_to_S_clear.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 4;
        ascii_to_string.add(A_to_S_clear, constraints);
        A_to_S_clear.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    A_to_S_input.setText("");
                    A_to_S_output.setText("");
                }
            });
        decrypt_tab.add(ascii_to_string, "Ascii to String");
        
        
        
        //Toggle to String Tab
        JPanel toggle_to_string = new JPanel();
        toggle_to_string.setLayout(new GridBagLayout());
        ImageIcon T_to_S_image = new ImageIcon("T to S.png");
        JLabel T_to_S_label = new JLabel(T_to_S_image, 0);
        constraints.gridx = 1;
        constraints.gridy = 0;  
        toggle_to_string.add(T_to_S_label, constraints);

        JLabel T_to_S_input_text = new JLabel("Input -");
        T_to_S_input_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 1;
        toggle_to_string.add(T_to_S_input_text, constraints);

        JTextArea T_to_S_input = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 1;
        toggle_to_string.add(T_to_S_input, constraints);

        JLabel T_to_S_output_text = new JLabel("Output -");
        T_to_S_output_text.setFont(new Font ("Segoe UI", 40, 20));
        constraints.gridx = 0;
        constraints.gridy = 2;
        toggle_to_string.add(T_to_S_output_text, constraints);

        JTextArea T_to_S_output = new JTextArea(4, 50);
        constraints.gridx = 1;
        constraints.gridy = 2;
        toggle_to_string.add(T_to_S_output, constraints);

        JButton T_to_S_button = new JButton("Enter");
        T_to_S_button.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 3;
        toggle_to_string.add(T_to_S_button, constraints);
        T_to_S_button.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    input = T_to_S_input.getText();
                    decryptToggle();
                    T_to_S_output.setText(output);
                }
            });

        JButton T_to_S_clear = new JButton("Clear");
        T_to_S_clear.setFont(new Font ("Segoe UI", 40, 15));
        constraints.gridx = 1;
        constraints.gridy = 4;
        toggle_to_string.add(T_to_S_clear, constraints);
        T_to_S_clear.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    T_to_S_input.setText("");
                    T_to_S_output.setText("");
                }
            });
        decrypt_tab.add(toggle_to_string, "Toggle to String");
        
        
        main_tab.add(encrypt_tab, "Encrypt");
        main_tab.add(decrypt_tab, "Decrypt");
        frame.add(main_tab);
        frame.setSize(1800, 1000);
        frame.setVisible(true);
    }
    
    public void welcomeFrame()
    {
        JFrame welcome = new JFrame();
        welcome.setTitle("WELCOME");
        JPanel welcome_panel = new JPanel();
        welcome_panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(25, 25, 25, 25);
        
        //Label with welcome text
        JLabel welcome_label = new JLabel("Welcome!!!");
        welcome_label.setFont(new Font ("Segoe UI", 40, 200));
        constraints.gridx = 1;
        constraints.gridy = 0;
        welcome_panel.add(welcome_label, constraints);

        //Button to begin program
        JButton enter_button = new JButton ("Begin");
        constraints.gridx = 1;
        constraints.gridy = 1;
        welcome_panel.add(enter_button, constraints);
        enter_button.addActionListener (new ActionListener()
            {
                @Override
                public void actionPerformed (ActionEvent e)
                {
                    welcome.setSize(0, 0);
                    frameCreate();
                }
            });
        
        welcome.add(welcome_panel);
        welcome.setSize(1800, 1000);
        welcome.setVisible(true);
    }

    public static void main (String [] args)  
    {  
        Main object = new Main();  
        object.welcomeFrame(); 
    }  

}
