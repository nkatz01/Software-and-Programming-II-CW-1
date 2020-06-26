package knapsack;

import java.util.Objects;

/**
 * Driver class for the Knapsack class of Coursework 1 in the Software and
 * Programming II module at BBK in 2018/9.
 *
 * @author Carsten Fuhs
 */
public class Coursework1Main {

    /*
     * The following is a tiny "home-grown" testing framework. We will see a more
     * advanced framework, JUnit, later in the module.
     */

    /** Index value for the next test. */
    private static int testNo = 1;

    /** Number of passed tests. */
    private static int passes = 0;

    /** Number of failed tests. */
    private static int fails = 0;

    /** Output for successful test. */
    private static final String YEA = "OK    "; // "PASSED";

    /** Output for unsuccessful test. */
    private static final String NAY = "FAILED";

    /**
     * Acceptable distance from expected value for double values, should be slightly
     * above 0.
     */
    private static final double DELTA = 1e-9;

    /**
     * Tests two int values for equality.
     *
     * Side effects: screen output of test result and increment of static counter
     * variables according to result.
     *
     * @param description to be used as part of the screen output
     * @param expected    the expected value
     * @param actual      the actual value
     */
    public static void testIntEqual(String description, int expected, int actual) {
        sideEffectsForTest(description, expected + "", actual + "", expected == actual);
    }

    /**
     * Tests two double values for equality (up to a small "delta").
     *
     * Side effects: screen output of test result and increment of static counter
     * variables according to result.
     *
     * @param description to be used as part of the screen output
     * @param expected    the expected value
     * @param actual      the actual value
     */
    public static void testDoubleEqual(String description, double expected, double actual) {
        sideEffectsForTest(description, expected + "", actual + "",
                expected - DELTA <= actual && actual <= expected + DELTA);
        // small rounding errors are ok
    }

    /**
     * Tests two Objects for equality. Works for Object and all its subclasses
     * (String, Item, ...).
     *
     * Side effects: screen output of test result and increment of static counter
     * variables according to result.
     *
     * @param description to be used as part of the screen output
     * @param expected    the expected value
     * @param actual      the actual value
     */
    public static void testObjectEqual(String description, Object expected, Object actual) {
        sideEffectsForTest(description, expected + "", actual + "", Objects.equals(expected, actual));
        // Objects.equals is graceful on null
    }

    /**
     * Helper method for the side effects of the tests for different data types
     * (here already converted to Strings): screen output and increment of static
     * counter variables.
     *
     * @param description description of the test to be printed
     * @param expected    String representation of the expected value
     * @param actual      String representation of the actual value
     * @param result      true: test has passed; false: test has failed
     */
    private static void sideEffectsForTest(String description, String expected, String actual, boolean result) {
        String output;
        if (result) {
            passes++;
            output = YEA;
        } else {
            fails++;
            output = NAY;
        }
        // System.out.println("Test " + testNo + ": " + description
        // + ", expected: " + expected + ", actual: " + actual
        // + " ===> " + output);
        System.out
                .println(output + " - Test " + testNo + ": " + description + ", expected: " + expected + ", actual: " + actual);
        testNo++;
    }

    /* The code to test our Knapsack in particular starts here. */

    /**
     * Constants for use in the tests.
     */
    private static final Item ITEM1 = new Item("Pen", 15);
    private static final Item ITEM2 = new Item("Letter", 20);
    private static final Item ITEM3 = new Item("Kilogramme", 1000);
    private static final Item ITEM4 = new Item("Soda", 400);
    private static final Item ITEM5 = new Item("Water", 395);
    private static final Item ITEM6 = new Item("Lemonade", 400);
    private static final Item ITEM7 = new Item("Kilo", 1000);
    private static final Item ITEM8 = new Item("Book", 120);

    /* Methods to create suitably constructed and modified Knapsack objects. */

    /**
     * @return an empty Knapsack
     */
    private static Knapsack makeEmptyKnapsack() {
        return new Knapsack();
    }

    /**
     * @return a Knapsack to which ITEM1 has been added
     */
    private static Knapsack makeAddOneItemKnapsack() {
        Knapsack k = makeEmptyKnapsack();
        k.add(ITEM1);
        return k;
    }

    /**
     * @return a Knapsack to which ITEM8 has been added twice
     */
    private static Knapsack makeAddTwoItemSameKnapsack() {
        Knapsack k = makeEmptyKnapsack();
        k.add(ITEM8);
        k.add(ITEM8);
        return k;
    }

    /**
     * @return a Knapsack to which ITEM8, null, ITEM8 have been added
     */
    private static Knapsack makeAddTwoItemSameAndNullKnapsack() {
        Knapsack k = makeEmptyKnapsack();
        k.add(ITEM8);
        k.add(null);
        k.add(ITEM8);
        return k;
    }

    /**
     * @return a Knapsack to which ITEM1, null, ITEM2 have been added
     */
    private static Knapsack makeAddTwoItemAndNullKnapsack() {
        Knapsack k = makeEmptyKnapsack();
        k.add(ITEM1);
        k.add(null);
        k.add(ITEM2);
        return k;
    }

    /**
     * @return a Knapsack on which addAll was invoked with ITEM1, null, ITEM2
     */
    private static Knapsack makeAddAllTwoItemAndNullKnapsack() {
        Knapsack k = makeEmptyKnapsack();
        Item[] items = { ITEM1, null, ITEM2 };
        k.addAll(items);
        return k;
    }

    /**
     * @return a Knapsack constructed with ITEM1, null, ITEM2 in the argument array
     */
    private static Knapsack makeConstructorTwoItemAndNullKnapsack() {
        Item[] items = { ITEM1, null, ITEM2 };
        Knapsack k = new Knapsack(items);
        return k;
    }

    /**
     * @return a Knapsack with both constructor arguments and a call to add()
     */
    private static Knapsack makeConstructorAddTwoItemAndNullKnapsack() {
        Item[] items = { ITEM1, null };
        Knapsack k = new Knapsack(items);
        k.add(ITEM2);
        return k;
    }

    /**
     * @return a Knapsack on which reset() was called right before returning
     */
    private static Knapsack makeResetKnapsack() {
        Item[] items = { ITEM1, null };
        Knapsack k = new Knapsack(items);
        k.add(ITEM2);
        k.reset();
        return k;
    }

    /**
     * @return a Knapsack on which the keepOnlyItemsWith() mutator has been called
     *         to remove some items
     */
    private static Knapsack makeKeepKnapsack() {
        Item[] items = { ITEM3, null, ITEM4, ITEM5, ITEM6, ITEM7, ITEM8, null, null, ITEM8 };
        Knapsack k = new Knapsack(items);
        k.add(ITEM2);
        k.keepOnlyItemsWith(400);
        return k;
    }

    /**
     * @return an array with two Knapsacks: the first one has ITEM1, ITEM2, and the
     *         second one has ITEM3
     */
    private static Knapsack[] makeTwoKnapsacks() {
        return new Knapsack[] { new Knapsack(new Item[] { ITEM1, ITEM2 }), new Knapsack(new Item[] { ITEM3 }) };
    }

    /**
     * Main method that drives the tests.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        Knapsack sack;
        sack = makeEmptyKnapsack();
        testObjectEqual("toString", "[]", sack.toString());
        sack = makeEmptyKnapsack();
        testObjectEqual("greatestItem", null, sack.greatestItem());
        sack = makeEmptyKnapsack();
        testIntEqual("numberOfItems", 0, sack.numberOfItems());
        sack = makeEmptyKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeEmptyKnapsack();
        testIntEqual("totalWeightInGrammes", 0, sack.totalWeightInGrammes());
        sack = makeEmptyKnapsack();
        testDoubleEqual("averageWeightInGrammes", -1.0, sack.averageWeightInGrammes());

        sack = makeAddOneItemKnapsack();
        testObjectEqual("toString", "[" + ITEM1 + "]", sack.toString());
        sack = makeAddOneItemKnapsack();
        testObjectEqual("greatestItem", ITEM1, sack.greatestItem());
        sack = makeAddOneItemKnapsack();
        testIntEqual("numberOfItems", 1, sack.numberOfItems());
        sack = makeAddOneItemKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeAddOneItemKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(10).numberOfItems());
        sack = makeAddOneItemKnapsack();
        testIntEqual("totalWeightInGrammes", 15, sack.totalWeightInGrammes());
        sack = makeAddOneItemKnapsack();
        testDoubleEqual("averageWeightInGrammes", 15.0, sack.averageWeightInGrammes());

        sack = makeAddTwoItemSameKnapsack();
        testObjectEqual("toString", "[" + ITEM8 + ", " + ITEM8 + "]", sack.toString());
        sack = makeAddTwoItemSameKnapsack();
        testObjectEqual("greatestItem", ITEM8, sack.greatestItem());
        sack = makeAddTwoItemSameKnapsack();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeAddTwoItemSameKnapsack();
        testIntEqual("makeNewKnapsackWith", 2, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeAddTwoItemSameKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(10).numberOfItems());
        sack = makeAddTwoItemSameKnapsack();
        testIntEqual("totalWeightInGrammes", 240, sack.totalWeightInGrammes());
        sack = makeAddTwoItemSameKnapsack();
        testDoubleEqual("averageWeightInGrammes", 120.0, sack.averageWeightInGrammes());

        sack = makeAddTwoItemSameAndNullKnapsack();
        testObjectEqual("toString", "[" + ITEM8 + ", " + ITEM8 + "]", sack.toString());

        sack = makeAddTwoItemAndNullKnapsack();
        testObjectEqual("greatestItem", ITEM2, sack.greatestItem());
        sack = makeAddTwoItemAndNullKnapsack();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 2, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(16).numberOfItems());
        sack = makeAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(15).numberOfItems());
        sack = makeAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(10).numberOfItems());
        sack = makeAddTwoItemAndNullKnapsack();
        testIntEqual("totalWeightInGrammes", 35, sack.totalWeightInGrammes());
        sack = makeAddTwoItemAndNullKnapsack();
        testDoubleEqual("averageWeightInGrammes", 17.5, sack.averageWeightInGrammes());

        sack = makeAddAllTwoItemAndNullKnapsack();
        testObjectEqual("greatestItem", ITEM2, sack.greatestItem());
        sack = makeAddAllTwoItemAndNullKnapsack();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeAddAllTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 2, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeAddAllTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(16).numberOfItems());
        sack = makeAddAllTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(15).numberOfItems());
        sack = makeAddAllTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(10).numberOfItems());
        sack = makeAddAllTwoItemAndNullKnapsack();
        testIntEqual("totalWeightInGrammes", 35, sack.totalWeightInGrammes());
        sack = makeAddAllTwoItemAndNullKnapsack();
        testDoubleEqual("averageWeightInGrammes", 17.5, sack.averageWeightInGrammes());

        sack = makeConstructorTwoItemAndNullKnapsack();
        testObjectEqual("greatestItem", ITEM2, sack.greatestItem());
        sack = makeConstructorTwoItemAndNullKnapsack();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeConstructorTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 2, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeConstructorTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(16).numberOfItems());
        sack = makeConstructorTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(15).numberOfItems());
        sack = makeConstructorTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(10).numberOfItems());
        sack = makeConstructorTwoItemAndNullKnapsack();
        testIntEqual("totalWeightInGrammes", 35, sack.totalWeightInGrammes());
        sack = makeConstructorTwoItemAndNullKnapsack();
        testDoubleEqual("averageWeightInGrammes", 17.5, sack.averageWeightInGrammes());

        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testObjectEqual("greatestItem", ITEM2, sack.greatestItem());
        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testIntEqual("numberOfItems", 2, sack.numberOfItems());
        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 2, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(16).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 1, sack.makeNewKnapsackWith(15).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(10).numberOfItems());
        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testIntEqual("totalWeightInGrammes", 35, sack.totalWeightInGrammes());
        sack = makeConstructorAddTwoItemAndNullKnapsack();
        testDoubleEqual("averageWeightInGrammes", 17.5, sack.averageWeightInGrammes());

        sack = makeResetKnapsack();
        testObjectEqual("toString", "[]", sack.toString());
        sack = makeResetKnapsack();
        testObjectEqual("greatestItem", null, sack.greatestItem());
        sack = makeResetKnapsack();
        testIntEqual("numberOfItems", 0, sack.numberOfItems());
        sack = makeResetKnapsack();
        testIntEqual("makeNewKnapsackWith", 0, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeResetKnapsack();
        testIntEqual("totalWeightInGrammes", 0, sack.totalWeightInGrammes());
        sack = makeResetKnapsack();
        testDoubleEqual("averageWeightInGrammes", -1.0, sack.averageWeightInGrammes());

        sack = makeKeepKnapsack();
        testIntEqual("greatestItem", 400, sack.greatestItem().getWeightInGrammes());
        sack = makeKeepKnapsack();
        testIntEqual("numberOfItems", 6, sack.numberOfItems());
        sack = makeKeepKnapsack();
        testIntEqual("makeNewKnapsackWith", 3, sack.makeNewKnapsackWith(120).numberOfItems());
        sack = makeKeepKnapsack();
        testIntEqual("totalWeightInGrammes", 1455, sack.totalWeightInGrammes());
        sack = makeKeepKnapsack();
        testDoubleEqual("averageWeightInGrammes", 242.5, sack.averageWeightInGrammes());

        testObjectEqual("heaviestKnapsack", null, Knapsack.heaviestKnapsack(new Knapsack[0]));

        Knapsack[] sacks = makeTwoKnapsacks();
        testIntEqual("heaviestKnapsack", 1000, Knapsack.heaviestKnapsack(sacks).totalWeightInGrammes());

        System.out.println();
        System.out.println(YEA + ": " + passes);
        System.out.println(NAY + ": " + fails);
    }

    /*
     *
     * OK - Test 1: toString, expected: [], actual: [] OK - Test 2: greatestItem,
     * expected: null, actual: null OK - Test 3: numberOfItems, expected: 0, actual:
     * 0 OK - Test 4: makeNewKnapsackWith, expected: 0, actual: 0 OK - Test 5:
     * totalWeightInGrammes, expected: 0, actual: 0 OK - Test 6:
     * averageWeightInGrammes, expected: -1.0, actual: -1.0 OK - Test 7: toString,
     * expected: [(Pen, 15g)], actual: [(Pen, 15g)] OK - Test 8: greatestItem,
     * expected: (Pen, 15g), actual: (Pen, 15g) OK - Test 9: numberOfItems,
     * expected: 1, actual: 1 OK - Test 10: makeNewKnapsackWith, expected: 1,
     * actual: 1 OK - Test 11: makeNewKnapsackWith, expected: 0, actual: 0 OK - Test
     * 12: totalWeightInGrammes, expected: 15, actual: 15 OK - Test 13:
     * averageWeightInGrammes, expected: 15.0, actual: 15.0 OK - Test 14: toString,
     * expected: [(Book, 120g), (Book, 120g)], actual: [(Book, 120g), (Book, 120g)]
     * OK - Test 15: greatestItem, expected: (Book, 120g), actual: (Book, 120g) OK -
     * Test 16: numberOfItems, expected: 2, actual: 2 OK - Test 17:
     * makeNewKnapsackWith, expected: 2, actual: 2 OK - Test 18:
     * makeNewKnapsackWith, expected: 0, actual: 0 OK - Test 19:
     * totalWeightInGrammes, expected: 240, actual: 240 OK - Test 20:
     * averageWeightInGrammes, expected: 120.0, actual: 120.0 OK - Test 21:
     * toString, expected: [(Book, 120g), (Book, 120g)], actual: [(Book, 120g),
     * (Book, 120g)] OK - Test 22: greatestItem, expected: (Letter, 20g), actual:
     * (Letter, 20g) OK - Test 23: numberOfItems, expected: 2, actual: 2 OK - Test
     * 24: makeNewKnapsackWith, expected: 2, actual: 2 OK - Test 25:
     * makeNewKnapsackWith, expected: 1, actual: 1 OK - Test 26:
     * makeNewKnapsackWith, expected: 1, actual: 1 OK - Test 27:
     * makeNewKnapsackWith, expected: 0, actual: 0 OK - Test 28:
     * totalWeightInGrammes, expected: 35, actual: 35 OK - Test 29:
     * averageWeightInGrammes, expected: 17.5, actual: 17.5 OK - Test 30:
     * greatestItem, expected: (Letter, 20g), actual: (Letter, 20g) OK - Test 31:
     * numberOfItems, expected: 2, actual: 2 OK - Test 32: makeNewKnapsackWith,
     * expected: 2, actual: 2 OK - Test 33: makeNewKnapsackWith, expected: 1,
     * actual: 1 OK - Test 34: makeNewKnapsackWith, expected: 1, actual: 1 OK - Test
     * 35: makeNewKnapsackWith, expected: 0, actual: 0 OK - Test 36:
     * totalWeightInGrammes, expected: 35, actual: 35 OK - Test 37:
     * averageWeightInGrammes, expected: 17.5, actual: 17.5 OK - Test 38:
     * greatestItem, expected: (Letter, 20g), actual: (Letter, 20g) OK - Test 39:
     * numberOfItems, expected: 2, actual: 2 OK - Test 40: makeNewKnapsackWith,
     * expected: 2, actual: 2 OK - Test 41: makeNewKnapsackWith, expected: 1,
     * actual: 1 OK - Test 42: makeNewKnapsackWith, expected: 1, actual: 1 OK - Test
     * 43: makeNewKnapsackWith, expected: 0, actual: 0 OK - Test 44:
     * totalWeightInGrammes, expected: 35, actual: 35 OK - Test 45:
     * averageWeightInGrammes, expected: 17.5, actual: 17.5 OK - Test 46:
     * greatestItem, expected: (Letter, 20g), actual: (Letter, 20g) OK - Test 47:
     * numberOfItems, expected: 2, actual: 2 OK - Test 48: makeNewKnapsackWith,
     * expected: 2, actual: 2 OK - Test 49: makeNewKnapsackWith, expected: 1,
     * actual: 1 OK - Test 50: makeNewKnapsackWith, expected: 1, actual: 1 OK - Test
     * 51: makeNewKnapsackWith, expected: 0, actual: 0 OK - Test 52:
     * totalWeightInGrammes, expected: 35, actual: 35 OK - Test 53:
     * averageWeightInGrammes, expected: 17.5, actual: 17.5 OK - Test 54: toString,
     * expected: [], actual: [] OK - Test 55: greatestItem, expected: null, actual:
     * null OK - Test 56: numberOfItems, expected: 0, actual: 0 OK - Test 57:
     * makeNewKnapsackWith, expected: 0, actual: 0 OK - Test 58:
     * totalWeightInGrammes, expected: 0, actual: 0 OK - Test 59:
     * averageWeightInGrammes, expected: -1.0, actual: -1.0 OK - Test 60:
     * greatestItem, expected: 400, actual: 400 OK - Test 61: numberOfItems,
     * expected: 6, actual: 6 OK - Test 62: makeNewKnapsackWith, expected: 3,
     * actual: 3 OK - Test 63: totalWeightInGrammes, expected: 1455, actual: 1455 OK
     * - Test 64: averageWeightInGrammes, expected: 242.5, actual: 242.5 OK - Test
     * 65: heaviestKnapsack, expected: null, actual: null OK - Test 66:
     * heaviestKnapsack, expected: 1000, actual: 1000
     *
     * OK : 66 FAILED: 0
     *
     */

}