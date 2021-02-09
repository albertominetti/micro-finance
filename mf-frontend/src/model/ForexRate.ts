export default class ForexRate {
  private _target: string;
  private _reference: string;
  private _value: number;
  private _date: Date;

  constructor(base: string, target: string, value: number, date: Date) {
    this._target = base;
    this._reference = target;
    this._value = value;
    this._date = date;
  }

  get target(): string {
    return this._target;
  }

  set target(value: string) {
    this._target = value;
  }

  get reference(): string {
    return this._reference;
  }

  set reference(value: string) {
    this._reference = value;
  }

  get value(): number {
    return this._value;
  }

  set value(value: number) {
    this._value = value;
  }

  get date(): Date {
    return this._date;
  }

  set date(value: Date) {
    this._date = value;
  }
}
