import ForexRate from "@/model/ForexRate";
import axios from "axios";

export default class ForexApi {
  public static async getForexRate(
    target: string,
    reference: string
  ): Promise<ForexRate | null> {
    try {
      const { data } = await axios.get(
        `/forex-service/rates/${target}/${reference}`
      );
      return new ForexRate(data.target, data.reference, data.value, data.date);
    } catch (e) {
      return null; // TODO handle it
    }
  }
}
