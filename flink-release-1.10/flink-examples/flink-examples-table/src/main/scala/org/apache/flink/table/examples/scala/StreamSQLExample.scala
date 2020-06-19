/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flink.table.examples.scala


import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.api.EnvironmentSettings
import org.apache.flink.table.api.scala._

/**
  * Simple example for demonstrating the use of SQL on a Stream Table in Scala.
  *
  * <p>Usage: <code>StreamSQLExample --planner &lt;blink|flink&gt;</code><br>
  *
  * <p>This example shows how to:
  *  - Convert DataStreams to Tables
  *  - Register a Table under a name
  *  - Run a StreamSQL query on the registered Table
  *
  */
object StreamSQLExample {

  // *************************************************************************
  //     PROGRAM
  // *************************************************************************

  def main(args: Array[String]): Unit = {

    val params = ParameterTool.fromArgs(args)
//    val planner = if (params.has("planner")) params.get("planner") else "flink"
    val planner = "blink"
    // set up execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val tEnv = if (planner == "blink") {  // use blink planner in streaming mode
      val settings = EnvironmentSettings.newInstance()
        .useBlinkPlanner()
        .inStreamingMode()
        .build()
      StreamTableEnvironment.create(env, settings)
    } else if (planner == "flink") {  // use flink planner in streaming mode
      StreamTableEnvironment.create(env)
    } else {
      System.err.println("The planner is incorrect. Please run 'StreamSQLExample --planner <planner>', " +
        "where planner (it is either flink or blink, and the default is flink) indicates whether the " +
        "example uses flink planner or blink planner.")
      return
    }

//    val order: DataStream[Order] = env.fromCollection(Seq(
//      Order(1, "beer", 3),
//      Order(2, "diaper", 4),
//      Order(3, "rubber", 2)))
//
//    order.timeWindowAll(Time.seconds(5))
//    val product: DataStream[Product] = env.fromCollection(Seq(
//      Product(1, "pen1", 6),
//      Product(2, "rubber1", 8),
//      Product(3, "beer1", 4)))
    val product: DataStream[Product] = env.fromCollection(Seq(
      Product(3, "beer1", 4)))

//    val orderC: DataStream[Order] = env.fromCollection(Seq(
//      Order(1, "penk", 33),
//      Order(2, "rubberk", 3444),
//      Order(3, "beerk", 1666)))


    // convert DataStream to Table
//    val tableA = tEnv.fromDataStream(orderA, 'user, 'product, 'amount)
    // register DataStream as Table
    tEnv.registerDataStream("Product", product, 'id, 'product, 'amount)
//    tEnv.registerDataStream("OrderC", orderC, 'productId, 'product, 'amount)
//    tEnv.registerDataStream("Orders", order, 'productId, 'product, 'amount)
//

    // union the two tables
//    val result = tEnv.sqlQuery(
//      s"""
//         |SELECT * FROM $tableA WHERE amount > 2
//         |UNION ALL
//         |SELECT * FROM OrderB WHERE amount < 2
//        """.stripMargin)

    //select * from OrderA a left join OrderB b on a.user=b.user left join OrderC c on a.user=c.user
  //SELECT * FROM Orders INNER JOIN Product ON Orders.productId = Product.id
//    val result = tEnv.sqlQuery(
//      s"""
//         |SELECT Orders.productId,Product.product,OrderC.amount
//         |FROM Orders LEFT JOIN Product ON Orders.productId = Product.id  LEFT JOIN OrderC ON Orders.productId = OrderC.productId WHERE Product.product IS NOT null
//        """.stripMargin)

    val result = tEnv.sqlQuery(
      s"""
         |SELECT *
         |FROM Product
        """.stripMargin)



//    val result = tEnv.sqlQuery(
//      s"""
//         |SELECT *
//         |FROM OrderC
//        """.stripMargin)

//    print(result)
//    result.toAppendStream[Order].print()
//    print(result.getQueryOperation)
    result.toRetractStream[Product].print()
//    result.filter("product = null").toRetractStream[Order].print()
//    result.where("")

//    print(env.getExecutionPlan)
    env.execute()
  }

  // *************************************************************************
  //     USER DATA TYPES
  // *************************************************************************

  case class Order(productId: Int, product: String, amount: Int)
  case class Product(id: Int, product: String, amount: Int)
  case class OR1(pid: Int, product: String, amount: Int)

  case class OOO(user: Int,product: String,amount: Int, Sproduct: String ,Samount: Int, Pproduct: String, Pamount: Int)

}
