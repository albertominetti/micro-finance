<template>
  <div>
    Hello World!
    <div v-if="forexRate">
      Requested forex rate is <br />
      Base: {{ forexRate.base }} <br />
      Target: {{ forexRate.target }} <br />
      Value: {{ forexRate.value }} <br />
      Date: {{ forexRate.date }}
    </div>
    <div v-else>
      Sorry, no Forex Rate at the moment...
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ForexApi from "@/api/ForexApi";
import ForexRate from "@/model/ForexRate";

@Component({})
export default class Home extends Vue {
  forexRate: ForexRate | null = null;

  async mounted(): Promise<void> {
    await this.fetchForexRate();
  }

  public async fetchForexRate(): Promise<void> {
    this.forexRate = await ForexApi.getForexRate("EUR", "USD");
  }
}
</script>
