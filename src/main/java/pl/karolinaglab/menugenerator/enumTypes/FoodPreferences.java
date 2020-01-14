package pl.karolinaglab.menugenerator.enumTypes;

public enum FoodPreferences {
    ALL{
        @Override
        public String toString() {
            return "Menu zwyczajne";
        }
    },
    VEGETARIAN{
        @Override
        public String toString() {
            return "Menu wegetariańskie";
        }
    },
    GLUTEN_FREE{
        @Override
        public String toString() {
            return "Menu bezglutenowee";
        }
    },
    LACTOSE_FREE{
        @Override
        public String toString() {
            return "Menu bez laktozy";
        }
    }
}
