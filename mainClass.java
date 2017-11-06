import javax.swing.*;
import java.awt.TextField;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class mainClass extends JFrame{

    static int flag = 0, flag2 = 0, quicks = 0, radixs = 0, heaps = 0;
    static public int count, whichsort;
    static long t1=0, t2=0, t3=0;
    static private long lastMeasured=0;
        public long tick() {
            long c = System.nanoTime();
            long result = c-lastMeasured;
            lastMeasured = c;
            return result;
        }
    
    Random rand = new Random();

    JPanel titlePanel, scorePanel, buttonPanel;
    JLabel label, label2, labela, labelb, labelc, labeld, labele, labelf, labelg, labelh;
    JButton readDataButton, sortButton, quickSortButton, radixSortButton, generateButton, heapSortButton, viewGraphButton, addButton, finishButton, automateButton;
    TextField textfield, textfield2;
    JComboBox<String> comboPivot = new JComboBox<String>();
    JComboBox<String> comboBase = new JComboBox<String>();
    
    public JPanel createContentPane (int flag2){

        JPanel totalGUI = new JPanel();
        
        totalGUI.setLayout(null);

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(10, 0);
        titlePanel.setSize(560, 30);
        totalGUI.add(titlePanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 0);
        buttonPanel.setSize(560, 330);
        totalGUI.add(buttonPanel);

        readDataButton = new JButton("Read Data");
        readDataButton.setLocation(20, 30);
        readDataButton.setSize(200, 50);
        readDataButton.addActionListener(new addButtonListener());
        buttonPanel.add(readDataButton);

        quickSortButton = new JButton("Quick Sort");
        quickSortButton.setLocation(20, 110);
        quickSortButton.setSize(200, 50);
        quickSortButton.addActionListener(new addButtonListener());
        quickSortButton.setEnabled(false);
        buttonPanel.add(quickSortButton);

        radixSortButton = new JButton("Radix Sort");
        radixSortButton.setLocation(20, 190);
        radixSortButton.setSize(200, 50);
        radixSortButton.addActionListener(new addButtonListener());
        radixSortButton.setEnabled(false);
        buttonPanel.add(radixSortButton);
        
        heapSortButton = new JButton("Heap Sort");
        heapSortButton.setLocation(20, 270);
        heapSortButton.setSize(200, 50);
        heapSortButton.addActionListener(new addButtonListener());
        heapSortButton.setEnabled(false);
        buttonPanel.add(heapSortButton);
        
        comboPivot.addItem("n/2");
        comboPivot.addItem("n/4");
        comboPivot.addItem("n/6");
        comboPivot.setLocation(320, 30);
        comboPivot.setSize(200, 50);
        comboPivot.setEnabled(false);
        buttonPanel.add(comboPivot);
        
        comboBase.addItem("2");
        comboBase.addItem("10");
        comboBase.addItem("16");
        comboBase.setLocation(320, 110);
        comboBase.setSize(200, 50);
        comboBase.setEnabled(false);
        comboBase.setSelectedItem("10");
        buttonPanel.add(comboBase);
        
        viewGraphButton = new JButton("View Graph");
        viewGraphButton.setLocation(320, 270);
        viewGraphButton.setSize(200, 50);
        viewGraphButton.addActionListener(new addButtonListener());
        viewGraphButton.setEnabled(false);
        buttonPanel.add(viewGraphButton);
        
        sortButton = new JButton("Sort!");
        sortButton.setLocation(320, 190);
        sortButton.setSize(200, 50);
        sortButton.addActionListener(new addButtonListener());
        sortButton.setEnabled(false);
        buttonPanel.add(sortButton);
        
        if(flag2 == 1)
        {
            quickSortButton.setEnabled(true);
            radixSortButton.setEnabled(true);
            heapSortButton.setEnabled(true);
        }
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    class addButtonListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == readDataButton)
        {
            flag = 1;
            createAndShowGUI(flag, flag2); 
            createtable();
        }
        else if(e.getSource() == quickSortButton)
        {
            comboPivot.setEnabled(true);
            comboBase.setEnabled(false);
            sortButton.setEnabled(true);
            whichsort = 1;
            //if(firstquicks == 0)
                quicks = 1;
            //else firstquicks = 2;
        }
        else if(e.getSource() == radixSortButton)
        {
            comboBase.setEnabled(true);
            comboPivot.setEnabled(false);
            sortButton.setEnabled(true);
            whichsort = 2;
            //if(firstradixs == 0)
                radixs = 1;
            //else firstradixs = 2;
        }
        else if(e.getSource() == heapSortButton)
        {
            whichsort = 3;
            sortButton.setEnabled(true);
            comboPivot.setEnabled(false);
            comboBase.setEnabled(false);
            //if(firstheapss == 0)
                heaps = 1;
            //else firstheapss = 2;
        }
       
        else if(e.getSource() == viewGraphButton)
        {
            System.out.println("t1 is " + t1);
            System.out.println("t2 is " + t2);
            System.out.println("t3 is " + t3);
//            SimpleBarChart barch = new SimpleBarChart();
//            
//            JFrame frame = new JFrame();
//            frame.setSize(350, 300);
//            frame.getContentPane().add(barch);
//            WindowListener winListener = new WindowAdapter() {public void windowClosing(WindowEvent event) {System.exit(0);}};
//            frame.addWindowListener(winListener);
//            frame.setVisible(true);
//        
//            barch.SimpleBarChart();
            flag = 3;
            createAndShowGUI(flag, flag2); 
            
        }
        else if(e.getSource() == addButton)
        {
            insertintable(textfield.getText());
            textfield.setText("");
            count+=1;
            //System.out.println("Count is"  + count);
        }
        else if(e.getSource() == finishButton)
        {
            flag = 0;
            createAndShowGUI(flag, flag2=1);
         }
        else if(e.getSource() == automateButton)
        {
            flag = 2;
            createAndShowGUI(flag, flag2); 
        }
        else if(e.getSource() == generateButton)
        {
            int n = Integer.parseInt(textfield2.getText());
            //System.out.println("In listener:" + n);
            populatetable(n);
            flag = 1;
            createAndShowGUI(flag, flag2);  
            count+=n;
            //System.out.println("Count is " + count);
        }
        else if(e.getSource() == sortButton)
        {
            System.out.println("In action listener of sortButton");
            comboPivot.setEnabled(false);
            comboBase.setEnabled(false);
            sortButton.setEnabled(false);
            if(whichsort==1)
            {
                QuickSort quicksort = new QuickSort();
                quicksort.QuickSort();
                //int[] numbers = getNumbers();
                //quicksort.sort(String.valueOf(comboPivot.getSelectedItem()), numbers);
                //setData(numbers, ct.whichsort);
                whichsort = 0;
            }
            else if(whichsort==2)
            {
                //radixSort(comboBase.getSelectedItem());
                RadixSort radixsort = new RadixSort();
                radixsort.RadixSort();
                whichsort = 0;
            }
            else if(whichsort==3)
            {
                HeapSort heapsort = new HeapSort();
                heapsort.HeapSort();
                whichsort = 0;
            }
            if(quicks == 1 && radixs == 1 && heaps == 1)
                viewGraphButton.setEnabled(true);
        }
       
    }
    }
    
    public JPanel createSecondPane() {
        JPanel totalGUI2 = new JPanel();
        
        totalGUI2.setLayout(null);
        
        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(10, 0);
        titlePanel.setSize(560, 30);
        totalGUI2.add(titlePanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 0);
        buttonPanel.setSize(560, 330);
        totalGUI2.add(buttonPanel);

        label = new JLabel("Enter Temperature:");
        label.setLocation(75, 75);
        label.setSize(200, 50);
        buttonPanel.add(label);
        
        textfield = new TextField("");
        textfield.setLocation(280, 75);
        textfield.setSize(100, 30);
        buttonPanel.add(textfield);
        
        addButton = new JButton("Add");
        addButton.setLocation(50, 250);
        addButton.setSize(100, 50);
        addButton.addActionListener(new addButtonListener());
        buttonPanel.add(addButton);
        
        finishButton = new JButton("Finish");
        finishButton.setLocation(200, 250);
        finishButton.setSize(100, 50);
        finishButton.addActionListener(new addButtonListener());
        buttonPanel.add(finishButton);
        
        automateButton = new JButton("Automate");
        automateButton.setLocation(350, 250);
        automateButton.setSize(100, 50);
        automateButton.addActionListener(new addButtonListener());
        buttonPanel.add(automateButton);
        
        totalGUI2.setOpaque(true);
        return totalGUI2;
    }
    
    public JPanel createThirdPane() {
        JPanel totalGUI3 = new JPanel();
        
        totalGUI3.setLayout(null);

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(10, 0);
        titlePanel.setSize(560, 30);
        totalGUI3.add(titlePanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 0);
        buttonPanel.setSize(560, 330);
        totalGUI3.add(buttonPanel);

        label2 = new JLabel("Enter n:");
        label2.setLocation(125, 75);
        label2.setSize(60, 50);
        buttonPanel.add(label2);
        
        textfield2 = new TextField("");
        textfield2.setLocation(200, 75);
        textfield2.setSize(150, 50);
        buttonPanel.add(textfield2);
        
        generateButton = new JButton("Generate");
        generateButton.setLocation(350, 250);
        generateButton.setSize(100, 50);
        generateButton.addActionListener(new addButtonListener());
        buttonPanel.add(generateButton);
        
        totalGUI3.setOpaque(true);
        return totalGUI3;
    }
    
    public JPanel createGraphPane() {
        JPanel totalGUIgraph = new JPanel();
        totalGUIgraph.setLayout(null);
        
        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(10, 0);
        titlePanel.setSize(560, 30);
        totalGUIgraph.add(titlePanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 0);
        buttonPanel.setSize(560, 330);
        totalGUIgraph.add(buttonPanel);
        
        labela = new JLabel("Number of temperature values:");
        labela.setLocation(20, 30);
        labela.setSize(200, 50);
        buttonPanel.add(labela);
        
        labelb = new JLabel();
        labelb.setText(String.valueOf(count));
        labelb.setLocation(320, 30);
        labelb.setSize(200, 50);
        buttonPanel.add(labelb);
        
        labelc = new JLabel("Quick Sort time:");
        labelc.setLocation(20, 110);
        labelc.setSize(200, 50);
        buttonPanel.add(labelc);
        
        labeld = new JLabel("" + Double.toString(t1));
        //t1/(java.lang.Math.pow(10,9))
        labeld.setText(String.valueOf(t1) + " ns");
        labeld.setLocation(320, 110);
        labeld.setSize(200, 50);
        buttonPanel.add(labeld);
        
        labele = new JLabel("Radix sort time:");
        labele.setLocation(20, 190);
        labele.setSize(200, 50);
        buttonPanel.add(labele);
        
        labelf = new JLabel("" + Double.toString(t2));
        labelf.setText(String.valueOf(t2) + "ns");
        labelf.setLocation(320, 190);
        labelf.setSize(200, 50);
        buttonPanel.add(labelf);
        
        labelg = new JLabel("Heap sort time:");
        labelg.setLocation(20, 270);
        labelg.setSize(200, 50);
        buttonPanel.add(labelg);
        
        labelh = new JLabel("" + Double.toString(t3));
        labelh.setLocation(320, 270);
        labelh.setText(String.valueOf(t3) + ("ns"));
        labelh.setSize(200, 50);
        buttonPanel.add(labelh);
        
        totalGUIgraph.setOpaque(true);
        return totalGUIgraph;
    }
    
    public JPanel createGraphPane2() {
        JPanel totalGUIgraph2 = new JPanel();
        totalGUIgraph2.setLayout(null);
        
        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        titlePanel.setLocation(10, 0);
        titlePanel.setSize(560, 30);
        totalGUIgraph2.add(titlePanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(0, 0);
        buttonPanel.setSize(560, 330);
        totalGUIgraph2.add(buttonPanel);
        
        totalGUIgraph2.setOpaque(true);
        return totalGUIgraph2;
    }

    public static class BarChart_AWT extends ApplicationFrame {
   
   public BarChart_AWT( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Score",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
      final String fiat = "Quick Sort";        
      final String audi = "Radix Sort";        
      final String ford = "Heap Sort";        
      final String speed = "n=" + count;        
//      final String millage = "n";        
//      final String userrating = "n";        
//      final String safety = "n";        
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

      dataset.addValue( t1 , fiat , speed );        
//      dataset.addValue( 3.0 , fiat , userrating );        
//      dataset.addValue( 5.0 , fiat , millage ); 
//      dataset.addValue( 5.0 , fiat , safety );           

      dataset.addValue( t2 , audi , speed );        
//      dataset.addValue( 6.0 , audi , userrating );       
//      dataset.addValue( 10.0 , audi , millage );        
//      dataset.addValue( 4.0 , audi , safety );

      dataset.addValue( t3 , ford , speed );        
//      dataset.addValue( 2.0 , ford , userrating );        
//      dataset.addValue( 3.0 , ford , millage );        
//      dataset.addValue( 6.0 , ford , safety );               

      return dataset; 
   }
   
   public void main( String[ ] args ) {
      
   }
}
    
    //public class InputDialogWithDropdownListbox {
  //public void funcb(String[] a) {
    //String[] choices = { "n/2", "n/4", "n/6"};
    //String[] choices2 = {"2", "10", "16"};
    //String input = (String) JOptionPane.showInputDialog(null, " ", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]); // Initial choice
    //System.out.println(input);
  //}
//}
    
    private static void createAndShowGUI(int flag, int flag2) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("[=] JButton Scores! [=]");

        mainClass demo = new mainClass();
        if(flag==0)
        {frame.setContentPane(demo.createContentPane(flag2));}
        else if(flag==1)
        {frame.setContentPane(demo.createSecondPane());}
        else if(flag==3)
        {frame.setContentPane(demo.createGraphPane());
        BarChart_AWT chart = new BarChart_AWT("Complexities Comparison","Complexities Comparison (in ns)");
        chart.pack( );        
        RefineryUtilities.centerFrameOnScreen( chart );        
        chart.setVisible( true ); }
        else
        {frame.setContentPane(demo.createThirdPane());}
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 380);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int flag = 0, flag2 = 0;
                createAndShowGUI(flag, flag2);
            }
        });
    }
    
    public static void createtable()
    {
      try
      {String myUrl = "jdbc:mysql://localhost:3306/temperatures?zeroDatetimeBehavious=convertToNull [root on default schema]";
      Connection conn = DriverManager.getConnection(myUrl, "root", "ImaySQL9!");
      Statement st = conn.createStatement();

      st.executeUpdate("DROP TABLE tempstable;");
      st.executeUpdate("CREATE TABLE tempstable (id int unsigned auto_increment not null, number BIGINT not null, PRIMARY KEY (id)) ENGINE = MYISAM;");
      conn.close(); 
      }
      
        catch (Exception e)
        {System.err.println("Got an exception!");
      System.err.println(e.getMessage());}
    }
        
    public void insertintable(String arg)
  {
    try
    {
      String myUrl = "jdbc:mysql://localhost:3306/temperatures?zeroDatetimeBehavious=convertToNull [root on default schema]";
      Connection conn = DriverManager.getConnection(myUrl, "root", "ImaySQL9!");
      Statement st = conn.createStatement();

      st.executeUpdate("INSERT INTO tempstable (number) " + "VALUES " + "("  + arg + ");");
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
    
    public void populatetable(int n)
    {  try
       {String myUrl = "jdbc:mysql://localhost:3306/temperatures?zeroDatetimeBehavious=convertToNull [root on default schema]";
        Connection conn = DriverManager.getConnection(myUrl, "root", "ImaySQL9!");
         Statement st = conn.createStatement();
        for (int i = 0; i < n; i++) {
                int val = rand.nextInt(5000);
                //int val = rand.nextInt(10001) - 5000;
                  st.executeUpdate("INSERT INTO tempstable (number) " + "VALUES " + "("  + val + ");");
        }
        conn.close(); 
       }
       
       catch (Exception e)
    { System.err.println("Got an exception!");
      System.err.println(e.getMessage());} 
        
    }
   
    
public class QuickSort {

    //public int[] numbers;
    //public String pivotIndex;

    public void sort(int left, int right, String pivotIndex, String[] numbers) 
    {
        if (right - left <= 0)
            return;
        else 
        {
            int pivotInd = 0;
        if(pivotIndex=="n/2")
        {pivotInd = (left + right) / 2;}
        else if(pivotIndex=="n/4")
        {pivotInd = (left + right) / 4;}
        else if(pivotIndex=="n/6")
        {pivotInd = (left + right) / 6;}
        
        
        
            swap(pivotInd, right, numbers);
            int pivot = Integer.parseInt(numbers[right]);
            int partition = partition(left, right, pivot, numbers);
            
//            System.out.println("pivot is " + pivot);
//            for(int j=0;j<numbers.length;j++)
//            System.out.print(numbers[j] + " ");
//        System.out.println("");
            
            sort(left, partition - 1, pivotIndex, numbers);
            sort(partition + 1, right, pivotIndex, numbers);
        }
    }
    
    public int partition(int left, int right, long pivot, String[] numbers) 
    {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) 
        {
            while (Integer.parseInt(numbers[++leftPtr]) < pivot)
                ;
            while (rightPtr > 0 && Integer.parseInt(numbers[--rightPtr]) > pivot)
                ;
 
            if (leftPtr >= rightPtr)
                break;
            else
                swap(leftPtr, rightPtr, numbers);
        }
        swap(leftPtr, right, numbers);
        return leftPtr;
    }
 
    public void swap(int dex1, int dex2, String[] numbers) 
    {
        //System.out.println("Swapping " + numbers[dex1] + " and " + numbers[dex2]);
        String temp = numbers[dex1];
        numbers[dex1] = numbers[dex2];
        numbers[dex2] = temp;
    }
 
    public void QuickSort()
    {   String[] numbers = getNumbers();
        tick();
        //System.out.println("In sort()" + ", pivotIndex is " + comboPivot.getSelectedItem() + "numbers.length is "+ numbers.length);
        sort(0, numbers.length-1, String.valueOf(comboPivot.getSelectedItem()), numbers);
        t1 = tick();
        System.out.println("After quicksort, numbers are");
        for(int j=0;j<numbers.length;j++)
            System.out.print(numbers[j] + " ");
        System.out.println("");
        
        //setData(numbers, whichsort);
    }

}

public class HeapSort 
{    
    private int N;
    /* Sort Function */
    public void sort(String arr[])
    {       
        heapify(arr);        
        for (int i = N; i > 0; i--)
        {
            swap(arr,0, i);
            N = N-1;
            maxheap(arr, 0);
        }
    }     
    
    public void heapify(String arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);        
    }
    
    public void maxheap(String arr[], int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && Integer.parseInt(arr[left]) > Integer.parseInt(arr[i]))
            max = left;
        if (right <= N && Integer.parseInt(arr[right]) > Integer.parseInt(arr[max]))        
            max = right;
 
        if (max != i)
        {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }    
    
    public void swap(String arr[], int i, int j)
    {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    }    
    
    public void HeapSort() 
    {
        String [] arr = getNumbers();
        tick();
        sort(arr);
        t3 = tick();
        System.out.println("After heapsort, numbers are");
        for(int k=0;k<arr.length;k++)
            System.out.print(arr[k] + " ");
        System.out.println("");
        
        //setData(arr, whichsort);
    }    
}

public class RadixSort 
{
    public void sort(String baser, String[] a)
    {
        int base = Integer.parseInt(baser);
        int i, exp = 1, n = a.length;
        //int m = Integer.parseInt(a[0]);
        String m = a[0];
        int[] b = new int[10000];
        //System.out.println("Entering for loop, a is ");
        //for(int j=0;j<a.length;j++)
        //    System.out.print(a[j] + " ");
        for (i = 1; i < n; i++)
            //if (Integer.parseInt(a[i]) > Integer.parseInt(m))
            //if(a[i].compareTo(m)>0)
              if(Integer.parseInt(a[i]) > Integer.parseInt(m))  
                m = a[i];
        //System.out.println("Entering while loop,");
        while ((Integer.parseInt(m)) / exp != 0)
        {
            int[] bucket = new int[10];
 
            for (i = 0; i < n; i++)
            {bucket[((Integer.valueOf(a[i])) / exp) % base]++;
            //System.out.println("1st loop: " + i);
            }
            for (i = 1; i < base; i++)
            {bucket[i] += bucket[i - 1];
            //System.out.println("2nd loop: " + i);
            }
            for (i = n - 1; i >= 0; i--)
            {b[--bucket[((Integer.parseInt(a[i])) / exp) % base]] = Integer.parseInt(a[i]);
            //System.out.println("3rd loop:" + i);
            }
            for (i = 0; i < n; i++)
            {a[i] = String.valueOf(b[i]);
            //System.out.println("4th loop: " + i);
            }
            exp *= base;        
        }
        System.out.println("After sorting, numbers are:");
       
    }    

    
    public void RadixSort() 
    {
        String [] arr = getNumbers();
        tick();
        //System.out.println("About to sort, numbers.length is " + arr.length);
        sort(String.valueOf(comboBase.getSelectedItem()),arr);
        //System.out.println("Sorted");
        t2 = tick();
        for(int j=0;j<arr.length;j++)
            System.out.print(arr[j] + " "); 
       System.out.println("");
        //setData(arr, whichsort);
    }    
}

//    public void setData(int[] numbers, int whichsort)
//    {
//        try
//        {
//            String myUrl = "jdbc:mysql://localhost:3306/temperatures?zeroDatetimeBehavious=convertToNull [root on default schema]";
//            Connection conn = DriverManager.getConnection(myUrl, "root", "ImaySQL9!");
//            Statement st = conn.createStatement();
//            String algo = "";
//            if(whichsort==1)
//                algo = "quicksort";
//            else if(whichsort==2)
//                algo = "radixsort";
//            else if(whichsort==3)
//                algo = "heapsort";
//            System.out.println("Before executing query, table name is " + algo);
//            st.executeUpdate("Drop table if exists " + algo + ";");
//            System.out.println("Dropped the table if it exists");
//            st.executeUpdate("create table " + algo + "(number bigint not null, PRIMARY KEY (number)) ENGINE = MYISAM;");
//            System.out.println("created, numbers.length is " + numbers.length);
//            for(int j=0;j<numbers.length;j++)
//                System.out.print(" " + numbers[j]);
//            for(int j=numbers.length;j>=0;j--)
//                {   System.out.println("Entered the for loop, numbers[0] is " + numbers[0]);
//                    st.executeUpdate("INSERT INTO " + algo + "(number) VALUES("+numbers[j]+");");
//                    System.out.println("Entered a num");
//                }
//            System.out.println("inserted");
//        }
//        
//        catch (Exception e)
//        {System.err.println("Got an exception!");
//        System.err.println(e.getMessage());} 
//    }

    public String[] getNumbers()
    {
        //System.out.println("Count is " + mainClass.count);
        try
        {
            String myUrl = "jdbc:mysql://localhost:3306/temperatures?zeroDatetimeBehavious=convertToNull [root on default schema]";
      Connection conn = DriverManager.getConnection(myUrl, "root", "ImaySQL9!");
      Statement st = conn.createStatement();
      String baser = "10";
      baser = String.valueOf(comboBase.getSelectedItem());
      System.out.println("baser is " + baser);
//      if(whichsort == 3 && baser=="2")
//          st.executeUpdate("SELECT conv(number,10,2) from tempstable");
//      else if(whichsort == 3 && baser == "16")
//          st.executeQuery("SELECT conv(number,10,16) from tempstable");
//      else
          st.executeQuery("SELECT number from tempstable;");
      ResultSet rs = st.getResultSet();
      String[] numbers = new String[count];
      int j=0;
      if(baser == "10")
      {
      while (rs.next()) {      
         numbers[j]=String.valueOf(rs.getInt("number"));   
         j++;
            }
      }
      else if(baser == "2")
      {
          while (rs.next()) {      
         numbers[j]=String.valueOf(rs.getInt("number"));   
         //System.out.println("numbers[j] is " + numbers[j]);
         //System.out.println(Integer.parseInt(numbers[j]));
         //System.out.println(Integer.toString(Integer.parseInt(numbers[j]), 2));
         //System.out.println(String.valueOf(Integer.parseInt(numbers[j]),2));
         //System.out.println(Integer.parseInt(numbers[j],2));
         //System.out.println(Integer.parseInt(numbers[j],2));
         //numbers[j] = String.valueOf(Integer.parseInt(numbers[j],2));
         numbers[j] = Integer.toString(Integer.parseInt(numbers[j]), 2);
         //System.out.println("numbers[j] in base 2 is " + String.valueOf(Integer.parseInt(numbers[j],2)));
         j++;
            }
      }
      else if(baser == "16")
      {
          while (rs.next()) {      
         numbers[j]=String.valueOf(rs.getInt("number"));   
         numbers[j] = Integer.toString(Integer.parseInt(numbers[j]), 16);
         j++;
            }
      }
        System.out.println("Exited while loop");
        System.out.println("In getnumbers, numbers are");
        for(int k=0;k<numbers.length;k++)
            System.out.print(numbers[k] + " ");
        System.out.println("");
        return numbers;
        }
        catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    } 
        return null;
    }
    
}