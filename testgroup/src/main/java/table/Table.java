package table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private WebElement tabElement;
    private WebDriver driver;
   // private List<WebElement>[] rowsWithColumns;

    public Table(WebElement tabElement, WebDriver driver){
        this.tabElement = tabElement;
        this.driver = driver;
    }

    public List<WebElement> getRows(){
        List<WebElement> rows = tabElement.findElements(By.xpath(".//tr"));
        rows.remove(0); // удаляем заголовки
        return rows;

    }
    //метод поиска заголовков
    public List<WebElement> getHeadings(){
        WebElement headingsRow = tabElement.findElement(By.xpath(".//tr[1]"));
        List<WebElement> headingColumns = headingsRow.findElements(By.xpath(".//th"));
        return headingColumns;
    }
    //разбиваем строки на заголовки (Лист листов)
    public List<List<WebElement>> getRowsWithColumns(){
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for (WebElement row :rows) {
           List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }
    //метод получения по номеру строки и по заголовку
    public List<Map<String,WebElement>> getRowsWithColumnsByHeadings(){
        List<Map<String,WebElement>> rowsWithColumnsByHeadings = new ArrayList<Map<String, WebElement>>();
        Map<String,WebElement> rowByHeadings;
        List<WebElement> headingColumns = getHeadings();
        //List<WebElement>[] rowsWithColumns = new List[0];
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        for(List<WebElement> row : rowsWithColumns){
            rowByHeadings = new HashMap<String, WebElement>();
            for(int i = 0; i < headingColumns.size(); i++){
                String heading = headingColumns.get(i).getText();
                WebElement cell = row.get(i);
                rowByHeadings.put(heading,cell);
            }
            rowsWithColumnsByHeadings.add(rowByHeadings);
            
        }
        return rowsWithColumnsByHeadings;
    }
    //метод поиска ячеек по номеру
    public Object getValueFromCell(int rowNumber, int columnNumber){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        WebElement cell = rowsWithColumns.get(rowNumber - 1).get(columnNumber - 1);
        return cell.getText();
    }
    public String getValueFromCell(int rowNumber, String columnName){
        List<Map<String,WebElement>> rowsWithColumnsByHeadings = getRowsWithColumnsByHeadings();
        return rowsWithColumnsByHeadings.get(rowNumber - 1).get(columnName).getText();

    }

}
