package pl.karolinaglab.menugenerator.enumTypes;

public enum MenuType {

    DAILY_MENU{
        @Override
        public String toString() {
            return "Menu jednodniowe";
        }
    },
    WEEKLY_MENU{
        @Override
        public String toString() {
            return "Menu tygodniowe";
        }
    }
}
