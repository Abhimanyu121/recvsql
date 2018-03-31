package org.codedocs.recy;

import android.provider.BaseColumns;

/**
 * Created by Abhimanyu on 3/30/2018.
 */

public class BeneficiaryContract {
    public static final class BeneficiaryEntry implements BaseColumns {

        public static final String TABLE_NAME = "beneficiary";
        public static final String COLUMN_BENEFICIARY_NAME = "beneficiary_name";
        public static final String COLUMN_BENEFICIARY_EMAIL = "beneficiary_email";
        public static final String COLUMN_BENEFICIARY_ADDRESS = "beneficiary_address";
        public static final String COLUMN_BENEFICIARY_COUNTRY = "beneficiary_country";
    }
}
