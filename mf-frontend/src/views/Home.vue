<template>
  <div>
    <v-card class="mx-auto my-2" max-width="350" outlined :loading="isLoading">
      <v-card-title>Hello World!</v-card-title>
      <v-card-subtitle>The requested Forex Rate is: </v-card-subtitle>
      <v-card-text v-if="forexRate">
        <v-chip outlined>Base</v-chip>
        {{ forexRate.base }}
        <v-divider class="my-1" />
        <v-chip outlined>Target</v-chip>
        {{ forexRate.target }}
        <v-divider class="my-1" />
        <v-chip outlined>Value</v-chip>
        {{ forexRate.value }}
        <v-divider class="my-1" />
        <v-chip outlined>Date</v-chip>
        {{ forexRate.date }}
      </v-card-text>
      <v-card-text v-else>No Forex Rate at the moment.</v-card-text>
      <v-card-actions>
        <v-btn @click="fetchForexRate">Fetch</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ForexApi from "@/api/ForexApi";
import ForexRate from "@/model/ForexRate";

@Component({})
export default class Home extends Vue {
  forexRate: ForexRate | null = null;
  isLoading = false;

  async mounted(): Promise<void> {
    await this.fetchForexRate();
  }

  public async fetchForexRate(): Promise<void> {
    const currencies = ["EUR", "USD", "CHF", "RUB"];
    const cur1 = currencies.splice(
      Math.floor(Math.random() * currencies.length),
      1
    );

    const cur2 = currencies.splice(
      Math.floor(Math.random() * currencies.length),
      1
    );

    this.isLoading = true;
    this.forexRate = await ForexApi.getForexRate(cur1[0], cur2[0]);
    this.isLoading = false;
  }
}
</script>
