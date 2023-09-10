package lk.ijse.jsp.bo;

import lk.ijse.jsp.bo.custom.impl.CustomerBOImpl;


    public class FactoryBO {
        private static FactoryBO factoryBO;

        private FactoryBO() {
        }

        public static FactoryBO getFactoryBO() {
            if (factoryBO == null) {
                return factoryBO = new FactoryBO();
            } else {
                return factoryBO;
            }
        }
        public SuperBO getInstance(BOTypes types) {
            switch (types) {
                case CUSTOMER:
                    return new CustomerBOImpl();
                case ITEM:
                    return null;
                default:
                    return null;
            }
        }
    }