package pl.karolinaglab.menugenerator.enumTypes;

public enum AmountType {
    GRAMS{
        @Override
        public String toString() {
            return "g";
        }
    },
    LITERS{
        @Override
        public String toString() {
            return "l";
        }
    },
    PIECES {
        @Override
        public String toString() {
            return "szt.";
        }
    }
}
