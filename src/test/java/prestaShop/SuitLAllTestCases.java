package prestaShop;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                CheckSortingByAscPrice.class,
                CheckNumbereOfFilteredElements.class

        }
)

public class SuitLAllTestCases {

}
