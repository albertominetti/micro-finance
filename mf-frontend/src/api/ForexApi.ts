import ForexRate from "@/model/ForexRate";
import axios from "axios";

export default class ForexApi {
  public static async getForexRate(
    base: string,
    target: string
  ): Promise<ForexRate | null> {
    try {
      const { data } = await axios.get(
        `/forex-service/rates/${base}/${target}`
      );
      return new ForexRate(data.base, data.target, data.value, data.date);
    } catch (e) {
      return null;
    }
  }
}
