package analysis.session

import analysis.session.Function.UserSessionAnalysisFunction1

/**
 * 大需求一：用户访问session统计
 */
object UserVisitSessionAnalysis {
  def main(args: Array[String]): Unit = {
    //小需求一：各个范围Session步长、访问时长占比统计
    UserSessionAnalysisFunction1.Demand1()

    //
  }
}
