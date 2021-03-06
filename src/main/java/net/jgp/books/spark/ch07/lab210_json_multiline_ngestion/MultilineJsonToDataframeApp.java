package net.jgp.books.spark.ch07.lab210_json_multiline_ngestion;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * Multiline ingestion JSON ingestion in a dataframe.
 * 
 * The data comes from the city of Durham, NC. You can freely download their
 * datasets from their portal at https://OpenDurham.nc.gov. .
 * 
 * @author jgp
 */
public class MultilineJsonToDataframeApp {

  /**
   * main() is your entry point to the application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    MultilineJsonToDataframeApp app =
        new MultilineJsonToDataframeApp();
    app.start();
  }

  /**
   * The processing code.
   */
  private void start() {
    // Creates a session on a local master
    SparkSession spark = SparkSession.builder()
        .appName("Multiline JSON to Dataframe")
        .master("local")
        .getOrCreate();

    // Reads a JSON, called countrytravelinfo.json, stores it in a dataframe
    Dataset<Row> df = spark.read()
        .format("json")
        .option("multiline", true)
        .load("data/countrytravelinfo.json");

    // Shows at most 3 rows from the dataframe
    df.show(3);
    df.printSchema();
  }
}
