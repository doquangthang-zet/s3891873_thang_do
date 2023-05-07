package vn.edu.rmit.group_project;

import java.util.Comparator;

/**
 * @author <Group 2>
 * s3891873 - Do Quang Thang
 * s3965673 - Phung Hoang Long
 * s3924489 - Du Tuan Vu
 * s3930338 - Nhat Mn
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
