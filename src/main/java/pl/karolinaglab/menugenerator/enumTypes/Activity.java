package pl.karolinaglab.menugenerator.enumTypes;

public enum Activity {

    NONE{
        @Override
        public String toString() {
            return "none";
        }
    },

    LOW{
        @Override
        public String toString() {
            return "low";
        }
    },
    AVERAGE{
        @Override
        public String toString() {
            return "average";
        }
    },
    HIGH{
        @Override
        public String toString() {
            return "high";
        }
    },

    VERY_HIGH{
        @Override
        public String toString() {
            return "very high";
        }
    }
}
