package vn.edu.rmit.individual_project;

import java.util.Comparator;

/**
 * @author <Do Quang Thang - S3891873>
 */

public class SortByTotalWeight implements Comparator <ShoppingCart> {

    @Override
    public int compare(ShoppingCart o1, ShoppingCart o2) {
        // Used for sorting in ascending order of total weight  
        if((o1.getTotalWeight() - o2.getTotalWeight()) > 0) return 1;
        if((o1.getTotalWeight() - o2.getTotalWeight()) < 0) return -1;
        return 0;
    }
    
}
