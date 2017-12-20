package com.cavaliersoftware.sample.client;

import com.cavaliersoftware.sample.util.MaskUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by IntelliJ IDEA.
 * User: john
 * Date: 12/18/17
 * Time: 9:48 AM
 * <p>
 */
public class ClientFrame extends JFrame implements MaskListener {

    private static Color ACTIVE = Color.RED;
    private static Color OFF = Color.CYAN;

    private JLabel[] labels = new JLabel[8];
    private MaskUtility utility = new MaskUtility();
    
    /**
     * Launches window that will highlight the example
     */
    public ClientFrame( ) throws HeadlessException {
        super( "Red denotes an earthquake has occurred" );
        setLocationRelativeTo(null);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        initUI();
    }

    /**
     * Populates the UI.
     */
    private void initUI() {
        JPanel mainPanel = new JPanel( );
        getContentPane().add( mainPanel );
        // add JLabels to the main panel
        createLabel( mainPanel, 1, "World" );
        createLabel( mainPanel, 2, "Antartica" );
        createLabel( mainPanel, 3, "Africa" );
        createLabel( mainPanel, 4, "Asia" );
        createLabel( mainPanel, 5, "Australia" );
        createLabel( mainPanel, 6, "Europe" );
        createLabel( mainPanel, 7, "N. America" );
        createLabel( mainPanel, 8, "S. America" );
    }

    private void createLabel( JPanel panel, int position, String text ) {
        JLabel label = new JLabel( text );
        label.setBackground( OFF );
        label.setForeground( Color.WHITE );
        label.setOpaque( true );
        panel.add( label );
        // store the label for future use (subtract 1 since arrays start at zero)
        labels[ position-1 ] = label;
    }

    private void updateLabel( int position, boolean active ) {
        labels[ position ].setBackground( active ? ACTIVE : OFF );
    }

    public static void main( String[] args ) {
        ClientFrame cf = new ClientFrame( );
        cf.showIt();
    }


    /**
     * Packs this instance of a JFrame to the appropriate size and sets it visible.
     */
    public void showIt() {
        this.pack();
        this.setVisible( true );

    }

    /**
     * Called when MaskEvents are created.  If the bitmask has changed, the appropriate UI labels are modified.
     * 
     * @param event
     */
    public void bitChanged( MaskEvent event ) {
        int mask = event.getBitMask();
        // handle if this gets the "end" flag
        if ( mask == Integer.MIN_VALUE ) {
            this.setTitle( "Not currently Receiving Data" );
        } else {
            for ( int i = 0; i < 8; i++ ) {
                updateLabel( i, utility.isBitOn( mask, i ) );
            }
        }
    }
}
