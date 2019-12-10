package pl.karolinaglab.menugenerator.enumTypes;

public enum Activity {

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
    }
}
